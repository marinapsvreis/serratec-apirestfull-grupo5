insert into public.endereco (cep, rua, bairro, cidade, numero, complemento, uf)
values  ('22250040', 'Praia Botafogo', 'Botafogo', 'Rio de Janeiro', 777, 'de 285/286 ao fim', 'RJ'),
        ('25629900', 'Rua Paulo Barbosa', 'Centro', 'Petrópolis', 222, '81', 'RJ'),
        ('22210903', 'Praia do Flamengo', 'Flamengo', 'Rio de Janeiro', 555, '66', 'RJ'),
        ('25620000', 'Rua do Imperador', 'Centro', 'Petrópolis', 888, 'até 552 - lado par', 'RJ');

insert into public.cliente (email, password, nome_completo, cpf, telefone, data_nascimento, id_endereco)
values  ('sophia@gmail.com', '123456', 'Sophia Araujo', '22222222222', '22222222222', '2003-10-22', 1),
        ('ester@gmail.com', '123456', 'Ester Baltazar', '55555555555', '24555555555', '2000-12-30', 2),
        ('breno@hotmail.com', '123456', 'Breno de Medeiros Seitz', '88888888888', '24888888888', '1994-03-28', 3),
        ('marina@gmail.com', '123456', 'Marina Portugal', '77777777777', '77777777777', '1992-02-20', 4);

insert into public.categoria (nome, descricao, imagem)
values  ('Funkos Harry Potter', 'Personagens de Harry Potter', 'https://i.imgur.com/n2WIMQp.jpg'),
        ('Funkos Overwatch', 'Heróis de Overwatch', 'https://i.imgur.com/gy5s6WY.jpg'),
        ('Funkos Star Wars', 'Personagens do Star Wars', 'https://i.imgur.com/nBSftus.jpg'),
        ('Funkos Disney', 'Personagens do fabuloso mundo da Disney', 'https://i.imgur.com/EH2XSsd.jpg'),
        ('Funkos Genshin Impact', 'Personagens do jogo de RPG Genshin Impact', 'https://i.imgur.com/ttLTcbS.jpg'),
        ('Funkos DC', 'Heróis da DC', 'https://i.imgur.com/l6TeCD1.jpg');

insert into public.produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, imagem, id_categoria)
values  ('Boba feet', 'Boneco Boba feet', 10, '2022-06-04', 110, 'https://i.imgur.com/soxgB88.jpg', 3),
        ('Leia Organa', 'Boneco Leia Organa', 10, '2022-06-04', 110, 'https://i.imgur.com/B4m4XaF.jpg', 3),
        ('Yoda', 'Boneco Yoda', 10, '2022-06-04', 110, 'https://i.imgur.com/nBSftus.jpg', 3),
        ('Batman', 'Boneco Batman', 10, '2022-06-04', 110, 'https://i.imgur.com/mpQJABN.jpg', 6),
        ('Lanterna Verde', 'Boneco Lanterna Verde', 10, '2022-06-04', 120, 'https://i.imgur.com/lmuo8M8.jpg', 6),
        ('Aquaman', 'Boneco Aquaman', 10, '2022-06-04', 120, 'https://i.imgur.com/lnaiuZ8.jpg', 6),
        ('Boneco Alvo Dumbledore', 'Boneco Alvo Dumbledore', 10, '2022-06-04', 130, 'https://i.imgur.com/aVk5CHA.jpg', 1),
        ('Boneco Severus Snape', 'Boneco Severus Snape', 10, '2022-06-04', 130, 'https://i.imgur.com/pykZsAv.jpg', 1),
        ('Boneca Branca de Neve', 'Princesa Branca de Neve', 15, '2022-06-04', 89, 'https://i.imgur.com/VzNkrlh.jpg', 4),
        ('Boneco Mickey', 'Mickey Mouse', 59, '2022-06-04', 95, 'https://i.imgur.com/TMEeNpx.jpg', 4),
        ('Boneco Aether', 'Personagem do jogo eletrônico Genshin Impact', 50, '2022-06-04', 88, 'https://i.imgur.com/heWt77n.jpg', 5),
        ('Boneca Paimon', 'Personagem do jogo eletrônico Genshin Impact 2', 15, '2022-06-04', 89, 'https://i.imgur.com/L3ouOJT.jpg', 5),
        ('Darth Vader', 'Boneco Darth Vader do Filme StarWars', 10, '2022-06-04', 120, 'https://i.imgur.com/A4roAvp.jpg', 3),
        ('Luke Skywalker', 'Boneco Luke Skywalker', 10, '2022-06-04', 110, 'https://i.imgur.com/jvMTAta.jpg', 3),
        ('Mulher-Maravilha', 'Boneco Mulher-Maravilha', 10, '2022-06-04', 120, 'https://i.imgur.com/l6TeCD1.jpg', 6),
        ('Super-Homem', 'Boneco Super-Homem', 10, '2022-06-04', 10, 'https://i.imgur.com/rtXdB6o.jpg', 6),
        ('Harry Potter', 'Boneco Harry Potter', 10, '2022-06-04', 130, 'https://i.imgur.com/n2WIMQp.jpg', 1),
        ('Hermione', 'Boneco Hermione', 10, '2022-06-04', 130, 'https://i.imgur.com/lA9lakV.jpg', 1),
        ('Rony Weasley', 'Boneco Rony Weasley', 10, '2022-06-04', 130, 'https://i.imgur.com/yyIbQnd.jpg', 1),
        ('Boneco Tristeza', 'Personagem do filme Divertida Mente', 50, '2022-06-04', 80, 'https://i.imgur.com/EH2XSsd.jpg', 4),
        ('Boneca Merida', 'Princesa Merida', 25, '2022-06-04', 125, 'https://i.imgur.com/4bDzu02.jpg', 4),
        ('Boneco Soul', 'Saxofonista Joe', 25, '2022-06-04', 90, 'https://i.imgur.com/qPQMKTo.jpg', 4),
        ('Boneca Lumine', 'Personagem do jogo eletrônico Genshin Impact Lumine', 25, '2022-06-04', 125, 'https://i.imgur.com/ttLTcbS.jpg', 5),
        ('Boneca Mei', 'Personagem Mei do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'https://i.imgur.com/gy5s6WY.jpg', 2),
        ('Boneca Reinhardt', 'Personagem Reinhardt do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'https://i.imgur.com/NQJYZRc.jpg', 2),
        ('Boneca Diva', 'Personagem Diva do jogo eletrônico Overwatch', 10, '2022-06-04', 100, 'https://i.imgur.com/iBafirR.jpg', 2);

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