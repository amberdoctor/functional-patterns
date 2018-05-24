package examples

import helpers.EC
import helpers.Emailer

class EmailSenderOneTimeConfig(private val ApiKey: String,
                               private val useSandbox: Boolean) {
    private val emailer = Emailer(ApiKey)
    // create and configure the emailer one time during construction.
    init {
        emailer.addRequestHeader(
                useSandbox,
                "Bearer $ApiKey")
    }
    fun sendEmail(emailContent: EC): String {
        val request = buildEmail(emailContent)
        println("Sent ${request.subjectText}")
        return emailer.api(request)
    }
    private fun buildEmail(emailContent: EC): EC {
        // uses none of the constructor params
        // do stuff
        return emailContent
    }
}


// with functions
fun buildEmailOT(emailContent: EC): EC {
    // do stuff
    return emailContent
}

/**
 * The emailer is only created one time then used each time.
 */
fun getEmailSenderOneTimeConfig(ApiKey: String, useSandbox: Boolean):
        (emailContent: EC) -> String {
    val emailer = Emailer(ApiKey)
    emailer.addRequestHeader(
            useSandbox,
            "Bearer $ApiKey")
    return {
        emailContent: EC ->
        val request = buildEmailOT(emailContent)
        println("Sent ${request.subjectText}")
        emailer.api(request)
    }
}

/**
 * Sample use of both styles.
 * This looks the same as the base EmailSender
 */
fun main(args: Array<String>){
    // some data - same for both
    val myEmailContent = EC("First Email", "World", "Hello")
    val myEmailContent2 = myEmailContent.copy(subjectText = "Second Email")

    // with functions
    // You can do this 1 time in the whole project
    val myEmailSender = getEmailSenderOneTimeConfig("ValidApiKey", true)

    myEmailSender(myEmailContent) // prints Sent First Email
    myEmailSender(myEmailContent2) // prints Sent Second Email


    // with class
    val myClassEmailSender = EmailSenderOneTimeConfig("ValidApiKey", true)

    myClassEmailSender.sendEmail(myEmailContent) // prints Sent First Email
    myClassEmailSender.sendEmail(myEmailContent2) // prints Sent Second Email

}