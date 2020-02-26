
INSERT INTO modal (id_modal,nome) 
VALUES (1,'Rodoviario');
INSERT INTO modal (id_modal,nome) 
VALUES (2,'Aerio');
INSERT INTO modal (id_modal,nome) 
VALUES (3,'Aguaviario');

INSERT INTO transportadoras (id_transportadora,email,nome,empresa,telefone,celular,rua,numero,bairro,cidade,uf,termo,whatsapp,cep,cnpj,logo) 
VALUES (1,'qualidade@teste.com','paulo querioz','verdun','81 9923 2343','11 9923 3434','rua test','1212','boa viagem','recife','PE',true,'81 993244 3434','51020-010', '42.318.949/0001-32', 'https://electroneum101.com/wp-content/uploads/2018/09/electroneum-logo-1024x1024.jpg');

INSERT INTO transportadoras (id_transportadora,email,nome,empresa,telefone,celular,rua,numero,bairro,cidade,uf,termo,whatsapp,cep,cnpj,logo) 
VALUES (2,'qualidade@teste.com','maria gloria','westmount','81 9923 2342','11 9923 3434','rua test','1212','boa viagem','recife','PE',true,'81 993244 3434','51020-010', '42.318.949/0001-84', 'https://image.shutterstock.com/z/stock-vector-vector-design-elements-for-your-company-logo-abstract-blue-icon-modern-logotipe-business-709133980.jpg');

INSERT INTO transportadoras (id_transportadora,email,nome,empresa,telefone,celular,rua,numero,bairro,cidade,uf,termo,whatsapp,cep,cnpj,logo) 
VALUES (3,'qualidade@teste.com','Fabio Maranhao','brossard','11 9923 3434','11 9923 3434','rua test','1212','liberdade','sao paulo','SP',true,'81 993244 3434','51020-010', '42.318.949/0001-84', 'https://image.shutterstock.com/z/stock-vector-vector-design-elements-for-your-company-logo-abstract-blue-icon-modern-logotipe-business-709133980.jpg');


INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(1,1);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(1,2);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(2,3);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(2,2);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(3,1);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(3,2);
INSERT INTO Transportadora_Modal(fk_id_transportadora,fk_id_modal) VALUES(3,3);


