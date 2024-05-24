package kotlinLibrary
import java.io.Serializable

class Book : Serializable {
    var id: Int = 0
    var code: String = ""
    var title: String = ""
    var authorLastName: String = ""
    var year: Int = 0
    var copiesCount: Int = 0

    constructor()

    constructor(id: Int, code: String, title: String, authorLastName: String, year: Int, copies: Int) {
        this.id = id
        this.code = code
        this.title = title
        this.authorLastName = authorLastName
        this.year = year
        this.copiesCount = copies
    }

    constructor(code: String, title: String, authorLastName: String, year: Int, copies: Int) {
        this.code = code
        this.title = title
        this.authorLastName = authorLastName
        this.year = year
        this.copiesCount = copies
    }

    override fun toString(): String {
        return "Book{" +
                "code='$code', " +
                "title='$title', " +
                "authorLastName='$authorLastName', " +
                "year=$year, " +
                "copies=$copiesCount" +
                '}'
    }
}
