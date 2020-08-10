package com.colamooon.shop.library.model

import org.apache.commons.lang3.builder.ToStringBuilder
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Item(
    var name: String,
    @Column(name = "sample_id")
    var sampleId: Long
) : BaseEntity() {
    override fun toString(): String = ToStringBuilder.reflectionToString(this)
}
