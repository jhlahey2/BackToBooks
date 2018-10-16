package com.lahey;

public class Book {

    private static int iBookCounter = 101;
    private String title;
    private Integer ISBN;

    public Book(String title){

        this.title = title;
        this.ISBN = new Integer(iBookCounter++);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBNString() {
        return ISBN.toString();
    }

    public int getISBN() {
        return ISBN;
    }

}//end public class Book
