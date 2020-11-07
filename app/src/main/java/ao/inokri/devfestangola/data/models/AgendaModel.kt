package ao.inokri.devfestangola.data.models

data class AgendaModel(
        var sessionDesc: String? = "",
        var sessionId: String? = "",
        var sessionStartTime: String? = "",
        var sessionTitle: String? = "",
        var sessionTotalTime: String? = "",
        var speakerDesc: String? = "",
        var speakerImage: String? = "",
        var speakerName: String? = "",
        var track: String? = "",
)