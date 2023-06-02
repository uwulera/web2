class MobilePhone(private val phoneNumber: String) {
    private val contacts = ArrayList<Contact>()

    fun addNewContact(contact: Contact): Boolean {
        if (findContact(contact) >= 0) {
            return false
        }
        contacts.add(contact)
        return true
    }

    fun updateContact(oldContact: Contact, newContact: Contact): Boolean {
        val position = findContact(oldContact)
        if (position < 0) {
            return false
        }
        contacts[position] = newContact
        return true
    }

    fun removeContact(contact: Contact): Boolean {
        val position = findContact(contact)
        if (position < 0) {
            return false
        }
        contacts.removeAt(position)
        return true
    }

    fun findContact(contact: Contact): Int {
        return contacts.indexOf(contact)
    }

    fun queryContact(name: String): Contact? {
        for (contact in contacts) {
            if (contact.name == name) {
                return contact
            }
        }
        return null
    }

    fun printContacts() {
        println("Contact List:")
        for (i in 0 until contacts.size) {
            println("${i+1}. ${contacts[i].name} -> ${contacts[i].phoneNumber}")
        }
    }
}

