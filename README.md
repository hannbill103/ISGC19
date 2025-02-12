# ISGC19

# Spring Boot API för Bokhantering

Detta är ett Spring Boot-projekt som tillhandahåller ett API för att hantera böcker. Det använder en H2-databas för lagring av bokdata och erbjuder CRUD-operationer via REST.

## Steg för att bygga och köra applikationen

### 1. Ladda hem dependencies
• Spring Boot DevTools
• Spring Web
• Spring Web Services
• Spring Data JPA
• H2 Database
• Lombok

### 2. Klona och bygg projektet
Klona detta repository till din dator och sedan navigera till projektmappen och bygg applikationen med Gradle

### 3. Kör programmet
 För att köra och testa API:t startar du BookapiApplication samt BookClient

### 4. Använd API:t
När applikationen körs, kommer ett användargränssnitt att visas i din terminal. Du kommer att få följande alternativ:
	1.	Hämta alla böcker
	2.	Hämta en specifik bok genom titel
	3.	Hämta böcker baserat på kategori
	4.	Skapa en ny bok
	5.	Avsluta
Välj ett alternativ genom att skriva in motsvarande nummer och tryck Enter. Du kommer därefter får feedback på ditt val.

