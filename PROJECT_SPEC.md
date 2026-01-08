
# To-Do List Backend API – Projektspezifikation

## Version
1.0

## Autor
Junior Ekane

## Technologien
Java 21+, Spring Boot 3+, Maven, Docker

## Ziel
Entwicklung einer REST-API zur Verwaltung von To-Do-Einträgen (CRUD) inklusive Dokumentation und Installationsanleitung.

---

# 1. Projektziele

Dieses Projekt dient als:

- Einstieg in Backend-Entwicklung mit Spring Boot
- Übung in Clean Code, architektonischer Struktur und API-Design
- Vorbereitung auf professionelle DevOps-Workflows (Docker, Versionierung)
- Grundlage für zukünftige Fullstack-Weiterentwicklungen
- Demonstration deiner Entwicklungsfähigkeiten gegenüber Kollegen / Product Owner

---

# 2. Funktionale Anforderungen

### 2.1. To-Do erstellen  
**POST /todos**

- Titel Pflicht  
- Beschreibung optional  
- Status standardmäßig: `OPEN`  
- Rückgabe: vollständiges To-Do-Objekt inkl. generierter ID  

### 2.2. Alle To-Dos abrufen  
**GET /todos**

- Rückgabe: Liste aller To-Dos  

### 2.3. Einzelnes To-Do abrufen  
**GET /todos/{id}**

- Rückgabe: To-Do-Objekt  
- Falls nicht gefunden → 404 Fehler  

### 2.4. To-Do aktualisieren  
**PUT /todos/{id}**

- Update von Titel, Beschreibung, Status  
- Falls nicht gefunden → 404 Fehler  

### 2.5. To-Do löschen  
**DELETE /todos/{id}**

- Falls nicht gefunden → 404 Fehler  
- Rückgabe: HTTP 204  

---

# 3. Nicht-funktionale Anforderungen

### 3.1. Code-Qualität & Clean Code

- JavaDoc für öffentliche Klassen/Methoden  
- Sprechende Variablen  
- Keine Logik im Controller  
- Keine toten Codeabschnitte  
- Keine magischen Strings/Zahlen  

### 3.2. Struktur

```
src/main/java/com/junior/todo
├── controller
├── service
├── repository
├── model
├── dto
└── exception
```

### 3.3. Tests

- Unit Tests für Service  
- MockMvc Tests für Controller  
- Mindestens 60% Coverage  

### 3.4. Dokumentation

- README.md  
- INSTALL.md  
- API.md  
- JavaDoc  

### 3.5. Docker

- Image muss startbar sein  
- Expose 8080  

---

# 4. API-Spezifikation (OpenAPI Style)

## ToDo Model

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Finish ToDo project",
  "status": "OPEN"
}
```

---

# 5. Technischer Plan / Architektur

### Controller  
- Validiert Input  
- Ruft Service auf  

### Service  
- Enthält Businesslogik  
- Fehlerhandling  

### Repository  
- Start: InMemory  
- Später: JPA  

### Exception Handling  
- GlobalExceptionHandler  

---

# 6. Testplan

### Unit Tests  
| Fall           | Erwartung             |
| -------------- | --------------------- |
| Erstellen      | ID generiert          |
| Abrufen        | Liste korrekt         |
| Update         | Änderungen übernommen |
| Löschen        | entfernt              |
| Nicht gefunden | Exception             |

### Integrationstests  
- POST /todos → 201  
- GET /todos → 200  
- PUT /todos/{id} → 200  
- DELETE → 204  

---

# 7. Build & Deployment

## Maven

```
mvn clean install
mvn spring-boot:run
```

## Dockerfile

Multi-stage Build, Port 8080, `java -jar`.

## Docker Befehle

```
docker build -t junior/todo-api:1.0 .
docker run -p 8080:8080 junior/todo-api:1.0
```

---

# 8. 📘 INSTALLATION.md (Kurzfassung)

1. Java 21 installieren  
2. Maven installieren  
3. Repo klonen  
4. Build ausführen  
5. Starten  
6. API testen  

---

# 9. 📗 BENUTZUNG.md

```
curl -X POST -H "Content-Type: application/json" -d '{"title":"Learn Java"}' http://localhost:8080/todos
```

---

# 10. 🎯 Definition of Done

- [ ] Alle Endpunkte implementiert  
- [ ] Tests vorhanden  
- [ ] README / INSTALL / API geschrieben  
- [ ] Dockerfile funktioniert  
- [ ] JavaDoc vorhanden  
- [ ] Clean Code eingehalten  

---

# Ende der Projektspezifikation