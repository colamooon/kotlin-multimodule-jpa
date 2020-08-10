package com.colamooon.shop.library.item.repository

import com.colamooon.shop.library.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>