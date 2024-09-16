package com.zcg.core_data.items

data class ItemSection(
    val sectionType: String,
    val items: List<Item>
)

data class Item(
    val title: String,
    val image: String
)
