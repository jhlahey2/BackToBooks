package com.lahey;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //hashmap ensure that each book has only one author
        HashMap<Book, Author> bookAuthorMap = new HashMap<Book, Author>();
        ArrayList<Author> authorList = new ArrayList<Author>();
        ArrayList<Book> bookList = new ArrayList<Book>();
        Scanner keyboard = new Scanner(System.in);

        String mainMenu = "1. Display All Books\n" +
                "2. Display All Authors\n" +
                "3. Add New Author\n" +
                "4. Add New Book\n" +
                "5. Link Book to Author\n" +
                "6. Quit";
        int iQuit = 6;
        int iChoice =0;

        initialize(authorList, bookList, bookAuthorMap);
        do{
            System.out.println(mainMenu);
            while( !keyboard.hasNextInt()){
                System.out.println("Please Enter a number");
                keyboard.nextLine();
            }
            iChoice = keyboard.nextInt();
            keyboard.nextLine();

            switch (iChoice) {
                case 1:
                    listAllBooks(bookList);
                    break;
                case 2:
                    listAllAuthors(authorList);
                    break;
                case 3:
                    addNewAuthor(keyboard, authorList);
                    break;
                case 4:
                    addNewBook(keyboard, bookList);
                    break;
                case 5:
                    linkBookAndAuthor(keyboard, bookList, authorList, bookAuthorMap);
                    break;
                case 6:
                    System.out.println("Quit");
                    break;
                default:
                    System.out.println("Please select one of the menu options");
                    break;

            }//end switch (iChoice)

        }while( !(iChoice == iQuit) );

    }//end public static void main(String[] args)

    public static void addNewAuthor(Scanner keyboard, ArrayList<Author> authorList){

        System.out.println("Add New Author");

        System.out.println("Enter First Name");
        String sFirstName = keyboard.nextLine();

        System.out.println("Enter Last Name");
        String sLastName = keyboard.nextLine();

        System.out.println("Enter Email");
        String sEmail = keyboard.nextLine();

        System.out.println("Enter Phone Number");
        String sPhone = keyboard.nextLine();

        Author newAuthor = new Author(sFirstName, sLastName, sEmail, sPhone);
        authorList.add(newAuthor);

    }//end public static Author createNewAuthor()

    public static void addNewBook(Scanner keyboard, ArrayList<Book> bookList){

        System.out.println("Add New Book");

        System.out.println("Enter Title");
        String sTitle = keyboard.nextLine();

        Book newBook = new Book(sTitle);

        bookList.add(newBook);

    }//end public static Author createNewAuthor()

    /**
     * This method requests a book ISBN from the user.  It then uses the input to find the Book object in the book
     * ArrayList.  It then checks the book:author map to see if the book has already been assigned an
     * author.  If the book has not been assigned an author, it asks for an author ID number, then assigns the book to
     * that author
     *
     * The book:author map is used to ensure that each book will have only one author
     *
     * @param keyboard
     * @param bookList
     * @param bookAuthorMap
     * @return
     */
    public static void linkBookAndAuthor(Scanner keyboard, ArrayList<Book> bookList, ArrayList<Author> authorList,
                                            HashMap<Book, Author> bookAuthorMap){

        int iAddMore = 1;
        int iQuit = 2;
        int iChoice =0;
        int iInputISBN = 0;
        int iInputAuthorID = 0;
        String sMenu = "Please choose\n1. Link more books to authors\n2. Quit";
        boolean bContinue = false;
        Author foundAuthor = null;
        Book foundBook = null;

        do{
            System.out.println("Enter book ISBN number");
            while( !keyboard.hasNextInt()){
                System.out.println("Please Enter a number");
                keyboard.nextLine();
            }
            iInputISBN = keyboard.nextInt();
            keyboard.nextLine();

            foundBook = null;
            for (Book book : bookList){

                if(book.getISBN() == iInputISBN){
                    foundBook = book;
                }
            }
            if( !(foundBook == null)){
                bContinue = true;
            }
            else {

                System.out.printf("Book %d not found\n", iInputISBN);
                bContinue = false;
            }

            if(bContinue){

                //if the book author map does contain the book map it to the author
                if( !(bookAuthorMap.containsKey(foundBook)) ){

                    System.out.println("Enter Author ID number");
                    while( !keyboard.hasNextInt()){
                        System.out.println("Please Enter a number");
                        keyboard.nextLine();
                    }
                    iInputAuthorID = keyboard.nextInt();
                    keyboard.nextLine();

                    foundAuthor = null;
                    for(Author author : authorList){

                        if(author.getAuthorID() == iInputAuthorID){

                            foundAuthor = author;
                            Author temp = bookAuthorMap.put(foundBook, foundAuthor);

                            if( !(temp == null) ) {

                                foundAuthor.addToBooksWritten(foundBook);
                                System.out.printf("Book %s has been linked to Author %s %s",
                                        foundBook.getTitle(), foundAuthor.getFirstName(), foundAuthor.getLastName());
                            }
                            else{

                                System.out.println("Something bad happened.");
                            }

                        }//end if(author.getAuthorID() == iInputAuthorID)

                    }//end for(Author author : authorList)

                    //if author is not found
                    if (foundAuthor == null) {

                        System.out.println("Author not found");
                    }
                }
                else{

                    Author author = bookAuthorMap.get(foundBook);
                    System.out.printf("Book ISBN: %s Title: %s Already has Author: %s %s\n",
                            foundBook.getISBNString(), foundBook.getTitle(), author.getFirstName(), author.getLastName());

                    bContinue = false;

                }//end if( !(bookAuthorMap.containsKey(foundBook) )

            }//end if(bContinue)

            do{
                System.out.println(sMenu);
                while( !keyboard.hasNextInt() ){
                    System.out.println("Please Enter a number");
                    keyboard.nextLine();
                }
                iChoice = keyboard.nextInt();
                keyboard.nextLine();

            }while( !(iChoice == iAddMore) && !(iChoice == iQuit) );


        }while( !(iChoice == iQuit) );


    }//end public static boolean addBookToAuthor(Book)

    public static void listAllAuthors(ArrayList<Author> authorList){

        for(Author author : authorList){

            System.out.printf("Author: %s %s \n", author.getFirstName(), author.getLastName());
            System.out.printf("Author ID:  %d \n", author.getAuthorID());
            System.out.printf("Email:  %s \n", author.getEmailAddress());
            System.out.printf("Phone:  %s \n", author.getPhoneNumber());
            HashSet<Book> bookSet = author.getBooksWrittenSet();
            if(bookSet.size() > 0){
                StringBuffer buffer = new StringBuffer();
                buffer.append("Books:  ");
                String prefix = "";
                for(Book book : bookSet){
                    buffer.append(prefix);
                    buffer.append("Title: " + book.getTitle() + " ISBN: " + book.getISBNString() );
                    prefix = ", ";
                }
                System.out.println(buffer.toString());
                System.out.println("\n");
            }
        }
    }//end public static void listAllAuthors()

    public static void listAllBooks(ArrayList<Book> bookList) {

        for (Book book : bookList) {

            System.out.println("Book");
            System.out.printf("ISBN: %s Title: %s\n\n", book.getISBNString(), book.getTitle());
        }

    }//end public static void listAllBooks(ArrayList<Book> bookList)


    public static void initialize(ArrayList<Author> authorList, ArrayList<Book> bookList, HashMap<Book, Author> bookAuthorMap){

        Author author01 = new Author("Bob", "Woodward", "bob@email.com", "555-1212" );
        authorList.add(author01);
        Author author02 = new Author("Rudyard", "Kipling", "rudy@email.com", "555-1212" );
        authorList.add(author02);
        Author author03 = new Author("Harper", "Lee", "Harper@email.com", "555-1212" );
        authorList.add(author03);
        Author author04 = new Author("Nicolo", "Machiavelli", "Nicolo@email.com", "555-1212" );
        authorList.add(author04);

        Book book01 = new Book("Go Set A Watchman");
        bookList.add(book01);
        Book book02 = new Book("To Kill A Mockingbird");
        bookList.add(book02);
        Book book03 = new Book("Fear");
        bookList.add(book03);
        Book book04 = new Book("All The President's Men");
        bookList.add(book04);
        Book book05 = new Book("The Jungle Book");
        bookList.add(book05);
        Book book06 = new Book("Just So Stories");
        bookList.add(book06);
        Book book07 = new Book("The Prince");
        bookList.add(book07);

        //bob woodward
        author01.addToBooksWritten(book03);
        bookAuthorMap.put(book03, author01);

        author01.addToBooksWritten(book04);
        bookAuthorMap.put(book04, author01);

        //Rudyard Kipling
        author02.addToBooksWritten(book05);
        bookAuthorMap.put(book05, author02);

        author02.addToBooksWritten(book06);
        bookAuthorMap.put(book06, author02);
        //Harper Lee
        author03.addToBooksWritten(book01);
        bookAuthorMap.put(book01, author03);

        author03.addToBooksWritten(book02);
        bookAuthorMap.put(book02, author03);

    }//end public static void initialize()

}//end public static void main(String[] args)
