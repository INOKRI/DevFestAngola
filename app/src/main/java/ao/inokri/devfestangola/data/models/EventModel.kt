package ao.inokri.devfestangola.data.models

data class EventModel(
        var dateEnd: String? = "",
        var dateStart: String? = "",
        var description: String? = "",
        var id: String? = "",
        var image: String? = "",
        var isActive: Boolean? = false,
        var location: String? = "",
        var title: String? = "",
)