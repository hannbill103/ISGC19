package com.example.bookapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Klassen markeras som en entity för att kunna kopplad en till en databas
@Entity
public class Book {

    // en primärnyckel för entiteten som genereras automatiskt
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "titeln får inte vara tom")
    private String title;


    @NotBlank(message = "författare får inte vara tom")
    private String author;


    @Size(max = 500, message = "beskrivningen får inte vara över 500 tecken")
    private String description;


    private String publishedYear;

    // enum typ för att kategoring ska kunna lagras som en sträng i databasen
    @Enumerated(EnumType.STRING)
    private Category category;

    // konstrumtor för att jpa ska kunna hämta och skapa objekten
    public Book() {}

    // konstruktor för att skapa en bok
    public Book(String title, String description, String publishedYear, String author, Category category) {
        this.title = title;
        this.description = description;
        this.publishedYear = publishedYear;
        this.author = author;
        this.category = category;
    }

    // getters och setters för att hämta och uppdatera de olika fältens värden
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title; }
    public void setTitle(String title) {
        this.title = title; }

    public String getAuthor() {
        return author; }
    public void setAuthor(String author) {
        this.author = author; }

    public String getDescription() {
        return description; }
    public void setDescription(String description) {
        this.description = description; }

    public String getPublishedYear() {
        return publishedYear; }
    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear; }

    public Category getCategory() {
        return category; }
    public void setCategory(Category category) {
        this.category = category; }
}


