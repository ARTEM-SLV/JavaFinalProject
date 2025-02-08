package app.model;

public class Book {
    private String author;
    private String name;
    private Integer pageCount;

    private Book(String author, String name, Integer pageCount) {
        this.author = author;
        this.name = name;
        this.pageCount = pageCount;
    }

    public static class BookBuilder{
        private String author;
        private String name;
        private Integer pageCount;

        public BookBuilder author(String author)
        {
            this.author = author;
            return this;
        }

        public BookBuilder name(String name)
        {
            this.name = name;
            return this;
        }

        public BookBuilder pageCount(Integer pageCount)
        {
            this.pageCount = pageCount;
            return this;
        }

        public Book build()
        {
            return new Book(author,name,pageCount);
        }
    }
}
