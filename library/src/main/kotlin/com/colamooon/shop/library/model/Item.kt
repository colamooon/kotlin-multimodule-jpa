package com.colamooon.shop.library.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Item(
    var name: String,
    @Column(name = "sample_id")
    var sampleId: Long
) : BaseEntity()
