package examples

import helpers.EC
import helpers.Emailer


// with class
/**
 * Configuration is stored via the class constructor,
 * then used to configure emailer every time sendEmail is called.
 */
class EmailSender(private val ApiKey: String,
                  private val useSandbox: Boolean) {
    fun sendEmail(emailContent: EC): String {
        val emailer = Emailer(ApiKey)
        emailer.addRequestHeader(
                useSandbox,
                "Bearer $ApiKey")
        val request = buildEmail(emailContent)
        println("Sent ${request.subjectText}")
        return emailer.api(request)
    }
    fun buildEmail(emailContent: EC): EC {
        // uses none of the constructor params
        // do stuff
        return emailContent
    }
}


// with functions
fun buildEmail(emailContent: EC): EC {
    // do stuff
    return emailContent
}

/**
 * Configuration of the emailer happens every time it's used.
 * But you don't have to pass the config every time.
 */
fun getEmailSender(ApiKey: String, useSandbox: Boolean):
        (emailContent: EC) -> String {
    return {
        emailContent: EC ->
        val emailer = Emailer(ApiKey)
        emailer.addRequestHeader(
                useSandbox,
                "Bearer $ApiKey")
        val request = buildEmail(emailContent)
        println("Sent ${request.subjectText}")
        emailer.api(request)
    }
}

/**
 * Sample use of both styles.
 */
fun main(args: Array<String>){
    // some data - same for both
    val myEmailContent = EC("First Email", "World", "Hello")
    val myEmailContent2 = myEmailContent.copy(subjectText = "Second Email")

    // with functions
    // You can do this 1 time in the whole project
    val myEmailSender = getEmailSender("ValidApiKey", true)

    myEmailSender(myEmailContent) // prints Sent First Email
    myEmailSender(myEmailContent2) // prints Sent Second Email


    // with class
    val myClassEmailSender = EmailSender("ValidApiKey", true)

    myClassEmailSender.sendEmail(myEmailContent) // prints Sent First Email
    myClassEmailSender.sendEmail(myEmailContent2) // prints Sent Second Email
}
