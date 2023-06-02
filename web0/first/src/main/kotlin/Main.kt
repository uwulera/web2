fun main(args: Array<String>) {

    val mobilePhone = MobilePhone("11(111)111-11-11")

    println("\n add New Contact: ")
    mobilePhone.addNewContact(Contact("Anna", "31(0060)940-36-25"))
    mobilePhone.printContacts()

    println("\n update Contact: ")
    mobilePhone.updateContact(Contact("Anna", "31(0060)940-36-25"), Contact("Anna", "11(111)111-11-11"))
    mobilePhone.printContacts()

    println("\n remove Contact: ")
    mobilePhone.removeContact(Contact("Anna", "11(111)111-11-11"))
    mobilePhone.printContacts()

    println("\n find Contact: ")
    val position = mobilePhone.findContact(Contact("Anna", "11(111)111-11-11"))
    if (position >= 0) {
        println("Контакт существует")
    }
    else {
        println("Контакт не существует")
    }

    println("\n add New Contact: ")
    mobilePhone.addNewContact(Contact("Anna", "31(0060)940-36-25"))
    mobilePhone.printContacts()

    println("\n query Contact: ")
    val foundContact = mobilePhone.queryContact("Anna")
    if (foundContact != null) {
        println("${foundContact.name} -> ${foundContact.phoneNumber}")
    }
}


