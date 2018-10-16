package com.lahey;

import java.util.HashSet;


public class Author {

    //static count for unique author identification
    private static int authorCounter = 301;

    //HashSet ensures that book is not added to more than one author
    int authorID;
    private HashSet<Book> booksWrittenSet;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public Author(String first, String last, String email, String phone){

        this.authorID = authorCounter++;
        this.firstName = first;
        this.lastName = last;
        this.emailAddress = email;
        this.phoneNumber = phone;
        booksWrittenSet = new HashSet<Book>();

    }

    public int getAuthorID() {
        return authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashSet getBooksWrittenSet() {
        return booksWrittenSet;
    }

    public boolean addToBooksWritten(Book book){

        return booksWrittenSet.add(book);
    }

}//end public class Author
