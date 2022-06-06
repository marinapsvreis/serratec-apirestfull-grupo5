
insert into public.endereco (id_endereco, cep, rua, bairro, cidade, numero, complemento, uf)
values  (2, '22250040', 'Praia Botafogo', 'Botafogo', 'Rio de Janeiro', 777, 'de 285/286 ao fim', 'RJ'),
        (3, '25629900', 'Rua Paulo Barbosa', 'Centro', 'Petrópolis', 222, '81', 'RJ'),
        (4, '22210903', 'Praia do Flamengo', 'Flamengo', 'Rio de Janeiro', 555, '66', 'RJ'),
        (5, '25620000', 'Rua do Imperador', 'Centro', 'Petrópolis', 888, 'até 552 - lado par', 'RJ'),
        (6, '17560292', 'Avenida Paulista', 'Jardim Aeroporto I', 'Vera Cruz', 1500, 'de 1800/1801 a 1828/1829', 'SP'),
        (7, '17560292', 'Avenida Paulista', 'Jardim Aeroporto I', 'Vera Cruz', 1810, 'de 1800/1801 a 1828/1829', 'SP');

insert into public.cliente (id_cliente, email, nome_completo, cpf, telefone, data_nascimento, id_endereco)
values  (2, 'sophia@gmail.com', 'Sophia Araujo', '22222222222', '22222222222', '2003-10-22', 3),
        (3, 'ester@gmail.com', 'Ester Baltazar', '55555555555', '24555555555', '2000-12-30', 4),
        (4, 'breno@hotmail.com', 'Breno de Medeiros Seitz', '88888888888', '24888888888', '1994-03-28', 5),
        (1, 'marina@gmail.com', 'Marina Portugal', '77777777777', '77777777777', '1992-02-20', 7);

insert into public.categoria (id_categoria, nome, descricao)
values  (1, 'Funkos Harry Potter', 'Personagens de Harry Potter'),
        (2, 'Funkos Overwatch', 'Heróis de Overwatch'),
        (3, 'Funkos Star Wars', 'Personagens do Star Wars'),
        (4, 'Funkos Disney', 'Personagens do fabuloso mundo da Disney'),
        (5, 'Funkos Genshin Impact', 'Personagens do jogo de RPG Genshin Impact'),
        (6, 'Funkos DC', 'Heróis da DC');

insert into public.produto (id_produto, nome, descricao, qtd_estoque, data_cadastro, valor_unitario, imagem, id_categoria)
values  (4, 'Boba feet', 'Boneco Boba feet', 10, '2022-06-04', 110, 'Boneco Boba feet', 3),
        (5, 'Leia Organa', 'Boneco Leia Organa', 10, '2022-06-04', 110, 'Boneco Leia Organa', 3),
        (6, 'Yoda', 'Boneco Yoda', 10, '2022-06-04', 110, 'Boneco Yoda', 3),
        (7, 'Batman', 'Boneco Batman', 10, '2022-06-04', 110, 'Boneco Batman', 6),
        (9, 'Lanterna Verde', 'Boneco Lanterna Verde', 10, '2022-06-04', 120, 'Boneco Lanterna Verde', 6),
        (10, 'Aquaman', 'Boneco Aquaman', 10, '2022-06-04', 120, 'Boneco Aquaman', 6),
        (15, 'Boneco Alvo Dumbledore', 'Boneco Alvo Dumbledore', 10, '2022-06-04', 130, 'Boneco Alvo Dumbledore', 1),
        (16, 'Boneco Severus Snape', 'Boneco Severus Snape', 10, '2022-06-04', 130, 'Boneco Severus Snape', 1),
        (20, 'Boneca Branca de Neve', 'Princesa Branca de Neve', 15, '2022-06-04', 89, 'BrancaDeNeve', 4),
        (21, ' Boneco Mickey', 'Mickey Mouse', 59, '2022-06-04', 95, 'MickeyMouse', 4),
        (22, 'Boneco Aether', 'Personagem do jogo eletrônico Genshin Impact', 50, '2022-06-04', 88, 'Aether', 5),
        (24, 'Boneca Paimon', 'Personagem do jogo eletrônico Genshin Impact 2', 15, '2022-06-04', 89, 'Paimon', 5),
        (2, 'Darth Vader', 'Boneco Darth Vader do Filme StarWars', 10, '2022-06-04', 120, 'C:\temp\serratec\apitrabalhofinal\produto.2.image.png', 3),
        (3, 'Luke Skywalker', 'Boneco Luke Skywalker', 10, '2022-06-04', 110, 'C:\temp\serratec\apitrabalhofinal\produto.3.image.png', 3),
        (8, 'Mulher-Maravilha', 'Boneco Mulher-Maravilha', 10, '2022-06-04', 120, 'C:\temp\serratec\apitrabalhofinal\produto.8.image.png', 6),
        (11, 'Super-Homem', 'Boneco Super-Homem', 10, '2022-06-04', 10, 'C:\temp\serratec\apitrabalhofinal\produto.11.image.png', 6),
        (12, 'Harry Potter', 'Boneco Harry Potter', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.12.image.png', 1),
        (13, 'Hermione', 'Boneco Hermione', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.13.image.png', 1),
        (14, 'Rony Weasley', 'Boneco Rony Weasley', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.14.image.png', 1),
        (17, 'Boneco Tristeza', 'Personagem do filme Divertida Mente', 50, '2022-06-04', 80, 'C:\temp\serratec\apitrabalhofinal\produto.17.image.png', 4),
        (18, 'Boneca Merida', 'Princesa Merida', 25, '2022-06-04', 125, 'C:\temp\serratec\apitrabalhofinal\produto.18.image.png', 4),
        (19, 'Boneco Soul', 'Saxofonista Joe', 25, '2022-06-04', 90, 'C:\temp\serratec\apitrabalhofinal\produto.19.image.png', 4),
        (23, 'Boneca Lumine', 'Personagem do jogo eletrônico Genshin Impact Lumine', 25, '2022-06-04', 125, 'C:\temp\serratec\apitrabalhofinal\produto.23.image.png', 5),
        (25, 'Boneca Mei', 'Personagem Mei do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.25.image.png', 2),
        (26, 'Boneca Reinhardt', 'Personagem Reinhardt do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.26.image.png', 2),
        (27, 'Boneca Diva', 'Personagem Diva do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.27.image.png', 2);

insert into public.pedido (id_pedido, data_pedido, data_entrega, data_envio, status, id_cliente, valor_bruto_total, valor_liquido_total, valor_desconto_total)
values  (2, '2022-06-04', null, null, false, 2, 260.00, 227.50, 58.50),
        (3, '2022-06-04', null, null, false, 3, 215.00, 202.50, 25.00),
        (4, '2022-06-04', null, null, false, 4, 230.00, 230.00, 0.00),
        (1, '2022-06-04', '2022-06-11', '2022-06-05', true, 1, 220.00, 193.00, 39.00);

insert into public.item_pedido (id_item_pedido, quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_pedido, id_produto)
values  (1, 1, 120.00, 10.00, 120.00, 108.00, 1, 2),
        (2, 1, 100.00, 15.00, 100.00, 85.00, 1, 25),
        (3, 1, 130.00, 20.00, 130.00, 104.00, 2, 13),
        (4, 1, 130.00, 5.00, 130.00, 123.50, 2, 15),
        (5, 1, 125.00, 10.00, 125.00, 112.50, 3, 18),
        (6, 1, 90.00, 0.00, 90.00, 90.00, 3, 19),
        (7, 1, 110.00, 0.00, 110.00, 110.00, 4, 7),
        (8, 1, 120.00, 0.00, 120.00, 120.00, 4, 8);
