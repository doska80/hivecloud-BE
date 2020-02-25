package br.com.hivecloud.transportadora.web.utils;

import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
@Component
public class BeanMapper extends DozerBeanMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanMapper.class);

	public BeanMapper() {
		super();
	}

	public BeanMapper(List<String> mappingFiles) {
		super(mappingFiles);
	}

	public <Ta, Tb> List<Tb> mapToList(Iterable<Ta> source, Class<Tb> destinationElementClass) throws MappingException {
		return StreamSupport.stream(source.spliterator(), false).map(element -> map(element, destinationElementClass))
				.collect(Collectors.toList());
	}

	public <Ta, Tb> List<Tb> mapToList(Collection<Ta> source, Class<Tb> destinationElementClass)
			throws MappingException {
		return source.stream().map(element -> map(element, destinationElementClass)).collect(Collectors.toList());
	}

	public <Ta, Tb> Page<Tb> mapToPage(Page<Ta> source, Class<Tb> destinationElementClass) throws MappingException {
		return source.map(element -> map(element, destinationElementClass));
	}

	public <Ta, Tb> Slice<Tb> mapToSlice(Slice<Ta> source, Class<Tb> destinationElementClass) throws MappingException {
		return source.map(element -> map(element, destinationElementClass));
	}

	/**
	 * Should not use this method, it causes dangerous behaviors !
	 * 
	 * @param source
	 * @param destination
	 * @param mapId
	 * @throws MappingException
	 */
	@Deprecated
	public void mapNotNull(Object source, Object destination, String mapId) throws MappingException {
		Object sourceMapped = super.map(source, destination.getClass(), mapId);
		copyPropertiesIgnoringNullValues(sourceMapped, destination);
	}

	/**
	 * Should not use this method, it causes dangerous behaviors !
	 * 
	 * @param source
	 * @param destination
	 * @throws MappingException
	 */
	@Deprecated
	public void mapNotNull(Object source, Object destination) throws MappingException {
		Object sourceMapped = super.map(source, destination.getClass());
		copyPropertiesIgnoringNullValues(sourceMapped, destination);
	}

	/**
	 * Operates a one-level trim on all String elements of the given object. Keeps
	 * null values.
	 * 
	 * @see "trimProps(T object, String... excludedProps)"
	 * @param object
	 * @param <T>
	 * @return
	 */
	public <T> void trimProps(T object) {
		trimProps(object, null);
	}

	/**
	 * Operates a one-level trim on all String elements of the given object. Keeps
	 * null values.
	 * 
	 * @param object
	 * @param excludedProps an array of properties to exclude (ex: "password")
	 * @param <T>
	 * @return
	 */
	public <T> void trimProps(T object, String... excludedProps) {
		if (object == null) {
			return;
		}

		Class<? extends Object> c = object.getClass();
		try {
			// Introspector usage to pick the getters conveniently thereby
			// excluding the Object getters
			for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(c, Object.class)
					.getPropertyDescriptors()) {
				String prop = propertyDescriptor.getName();

				// manage exlusions :
				if (excludedProps != null && Arrays.stream(excludedProps).anyMatch(p -> prop.equals(p))) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Property '" + prop + "' excluded from trimProps for object type "
								+ object.getClass().getName());
					}
					continue;
				}

				Method method = propertyDescriptor.getReadMethod();
				String name = method.getName();

				// If the current level of Property is of type String
				if (method.getReturnType().equals(String.class)) {
					String value = (String) method.invoke(object);
					if (value != null) {
						Method setter = c.getMethod("set" + name.substring(3), new Class<?>[] { String.class });
						if (setter != null)
							// Setter to trim and set the trimmed String value
							setter.invoke(object, value.trim());
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Strings cannot be trimmed because: ", e);
		}
	}

	private void copyPropertiesIgnoringNullValues(Object src, Object target) {
		if (src == null) {
			return;
		}
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	private String[] getNullPropertyNames(Object source) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
	}

}
