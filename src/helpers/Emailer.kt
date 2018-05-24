package helpers

// fake 3rd party emailer
class Emailer(val ApiKey: String) {

    fun addRequestHeader(sandbox: Boolean, moreStuff: String) {
        // do stuff with ApiKey
    }

    fun api(request: EC): String {
        // do stuff
        return "stuff done"
    }
}