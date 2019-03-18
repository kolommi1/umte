package cz.uhk.umte.ws

// instance se vytvoří až při volání
val stagService by lazy {
    StagService.create()
}