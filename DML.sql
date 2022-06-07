
insert into public.endereco (cep, rua, bairro, cidade, numero, complemento, uf)
values  ('22250040', 'Praia Botafogo', 'Botafogo', 'Rio de Janeiro', 777, 'de 285/286 ao fim', 'RJ'),
        ('25629900', 'Rua Paulo Barbosa', 'Centro', 'Petrópolis', 222, '81', 'RJ'),
        ('22210903', 'Praia do Flamengo', 'Flamengo', 'Rio de Janeiro', 555, '66', 'RJ'),
        ('25620000', 'Rua do Imperador', 'Centro', 'Petrópolis', 888, 'até 552 - lado par', 'RJ'),
        ('17560292', 'Avenida Paulista', 'Jardim Aeroporto I', 'Vera Cruz', 1500, 'de 1800/1801 a 1828/1829', 'SP'),

insert into public.cliente (email, nome_completo, cpf, telefone, data_nascimento, id_endereco)
values  ('sophia@gmail.com', 'Sophia Araujo', '22222222222', '22222222222', '2003-10-22', 1),
        ('ester@gmail.com', 'Ester Baltazar', '55555555555', '24555555555', '2000-12-30', 2),
        ('breno@hotmail.com', 'Breno de Medeiros Seitz', '88888888888', '24888888888', '1994-03-28', 3),
        ('marina@gmail.com', 'Marina Portugal', '77777777777', '77777777777', '1992-02-20', 4);

insert into public.categoria (nome, descricao)
values  ('Funkos Harry Potter', 'Personagens de Harry Potter'),
        ('Funkos Overwatch', 'Heróis de Overwatch'),
        ('Funkos Star Wars', 'Personagens do Star Wars'),
        ('Funkos Disney', 'Personagens do fabuloso mundo da Disney'),
        ('Funkos Genshin Impact', 'Personagens do jogo de RPG Genshin Impact'),
        ('Funkos DC', 'Heróis da DC');

insert into public.produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, imagem, id_categoria)
values  ('Boba feet', 'Boneco Boba feet', 10, '2022-06-04', 110, 'Boneco Boba feet', 3),
        ('Leia Organa', 'Boneco Leia Organa', 10, '2022-06-04', 110, 'Boneco Leia Organa', 3),
        ('Yoda', 'Boneco Yoda', 10, '2022-06-04', 110, 'Boneco Yoda', 3),
        ('Batman', 'Boneco Batman', 10, '2022-06-04', 110, 'Boneco Batman', 6),
        ('Lanterna Verde', 'Boneco Lanterna Verde', 10, '2022-06-04', 120, 'Boneco Lanterna Verde', 6),
        ('Aquaman', 'Boneco Aquaman', 10, '2022-06-04', 120, 'Boneco Aquaman', 6),
        ('Boneco Alvo Dumbledore', 'Boneco Alvo Dumbledore', 10, '2022-06-04', 130, 'Boneco Alvo Dumbledore', 1),
        ('Boneco Severus Snape', 'Boneco Severus Snape', 10, '2022-06-04', 130, 'Boneco Severus Snape', 1),
        ('Boneca Branca de Neve', 'Princesa Branca de Neve', 15, '2022-06-04', 89, 'BrancaDeNeve', 4),
        ('Boneco Mickey', 'Mickey Mouse', 59, '2022-06-04', 95, 'MickeyMouse', 4),
        ('Boneco Aether', 'Personagem do jogo eletrônico Genshin Impact', 50, '2022-06-04', 88, 'Aether', 5),
        ('Boneca Paimon', 'Personagem do jogo eletrônico Genshin Impact 2', 15, '2022-06-04', 89, 'Paimon', 5),
        ('Darth Vader', 'Boneco Darth Vader do Filme StarWars', 10, '2022-06-04', 120, 'C:\temp\serratec\apitrabalhofinal\produto.2.image.png', 3),
        ('Luke Skywalker', 'Boneco Luke Skywalker', 10, '2022-06-04', 110, 'C:\temp\serratec\apitrabalhofinal\produto.3.image.png', 3),
        ('Mulher-Maravilha', 'Boneco Mulher-Maravilha', 10, '2022-06-04', 120, 'C:\temp\serratec\apitrabalhofinal\produto.8.image.png', 6),
        ('Super-Homem', 'Boneco Super-Homem', 10, '2022-06-04', 10, 'C:\temp\serratec\apitrabalhofinal\produto.11.image.png', 6),
        ('Harry Potter', 'Boneco Harry Potter', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.12.image.png', 1),
        ('Hermione', 'Boneco Hermione', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.13.image.png', 1),
        ('Rony Weasley', 'Boneco Rony Weasley', 10, '2022-06-04', 130, 'C:\temp\serratec\apitrabalhofinal\produto.14.image.png', 1),
        ('Boneco Tristeza', 'Personagem do filme Divertida Mente', 50, '2022-06-04', 80, 'C:\temp\serratec\apitrabalhofinal\produto.17.image.png', 4),
        ('Boneca Merida', 'Princesa Merida', 25, '2022-06-04', 125, 'C:\temp\serratec\apitrabalhofinal\produto.18.image.png', 4),
        ('Boneco Soul', 'Saxofonista Joe', 25, '2022-06-04', 90, 'C:\temp\serratec\apitrabalhofinal\produto.19.image.png', 4),
        ('Boneca Lumine', 'Personagem do jogo eletrônico Genshin Impact Lumine', 25, '2022-06-04', 125, 'C:\temp\serratec\apitrabalhofinal\produto.23.image.png', 5),
        ('Boneca Mei', 'Personagem Mei do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.25.image.png', 2),
        ('Boneca Reinhardt', 'Personagem Reinhardt do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.26.image.png', 2),
        ('Boneca Diva', 'Personagem Diva do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'C:\temp\serratec\apitrabalhofinal\produto.27.image.png', 2);

insert into public.pedido (data_pedido, data_entrega, data_envio, status, id_cliente, valor_bruto_total, valor_liquido_total, valor_desconto_total)
values  ('2022-06-04', null, null, false, 2, 260.00, 227.50, 58.50),
        ('2022-06-04', null, null, false, 3, 215.00, 202.50, 25.00),
        ('2022-06-04', null, null, false, 4, 230.00, 230.00, 0.00),
        ('2022-06-04', '2022-06-11', '2022-06-05', true, 1, 220.00, 193.00, 39.00);

insert into public.item_pedido (quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_pedido, id_produto)
values  (1, 120.00, 10.00, 120.00, 108.00, 1, 2),
        (1, 100.00, 15.00, 100.00, 85.00, 1, 25),
        (1, 130.00, 20.00, 130.00, 104.00, 2, 13),
        (1, 130.00, 5.00, 130.00, 123.50, 2, 15),
        (1, 125.00, 10.00, 125.00, 112.50, 3, 18),
        (1, 90.00, 0.00, 90.00, 90.00, 3, 19),
        (1, 110.00, 0.00, 110.00, 110.00, 4, 7),
        (1, 120.00, 0.00, 120.00, 120.00, 4, 8);
