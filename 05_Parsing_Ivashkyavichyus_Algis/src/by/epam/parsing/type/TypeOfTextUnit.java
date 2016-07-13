package by.epam.parsing.type;

public enum TypeOfTextUnit {
    TEXT("text"),
    PARAGRAPH("paragraph"),
    SENTENCE("sentence"),
    LEXEME("lexeme"),
    WORD("word"),
    PUNCTUATION("punctuation"),
    FORMULA("formula");

    private String value;

    private TypeOfTextUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
