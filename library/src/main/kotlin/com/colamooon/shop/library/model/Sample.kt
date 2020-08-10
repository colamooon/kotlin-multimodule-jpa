package com.colamooon.shop.library.model

import org.apache.commons.lang3.builder.ToStringBuilder
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Sample(
    @Column(length = 100)
    @NotBlank
    var title: String,
    @Enumerated(EnumType.STRING)
    var status: SampleStatus = SampleStatus.INIT,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "sample_id", nullable = false, insertable = false, updatable = false)
    var items: MutableList<Item> = mutableListOf()
) : BaseEntity() {
    override fun toString(): String = ToStringBuilder.reflectionToString(this)
}

enum class SampleStatus {
    INIT, DOING, DONE
}