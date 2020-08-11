package com.colamooon.shop.library.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Sample(
    @Column(length = 100)
    @NotBlank
    var title: String,
    @Enumerated(EnumType.STRING)
    var status: SampleStatus = SampleStatus.INIT,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sample_id", nullable = false, insertable = false, updatable = false)
    var items: MutableList<Item> = mutableListOf()
) : BaseEntity()

enum class SampleStatus {
    INIT, DOING, DONE
}