package com.residencia.ecommerce.repository;

import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.ItemPedido;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
    List<ItemPedido> findByPedido(Pedido pedido);
    List<ItemPedido> findByProduto(Produto produto);
}
