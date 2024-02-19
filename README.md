RoomMate Raum Reservierungssystem
Beschreibung
Das RoomMate Raum Reservierungssystem ermöglicht es Benutzern, Arbeitsplätze in Räumen für bestimmte Zeiträume zu buchen. Jeder Arbeitsplatz verfügt über verschiedene Ausstattungen wie Monitor, Dockingstation, Maus, usw. Bei der Suchanfrage können Benutzer die gewünschte Ausstattung angeben, und das System zeigt verfügbare Arbeitsplätze an.

Admin Funktionen
Administratoren haben erweiterte Funktionen wie das Hinzufügen oder Entfernen von Ausstattungen für Arbeitsplätze und Räume.
Sie können auch Arbeitsplätze und Räume erstellen oder löschen.
Admins können alle Buchungen von Usern sehen
Zusätzlich können Administratoren Buchungen stornieren(löschen) und Arbeitsplätze für einen bestimmten Zeitraum sperren.
Die User müssen sich mit OAuth2 authentifizieren. Jeder User sieht nur seine eigenen Buchungen.



Technologie Stack
Java Version 21
Gradle 8.5
Spring Boot 3.1
Docker
PostgreSQL
OAuthApp in Github für die Anmeldung mit Github
Flyway für die Datenbankmigration
Thymeleaf, CSS Bootstrap, HTML
Installation
Klone das Repository: git clone https://github.com/dein-username/roommate.git

Wechsle in das Projektverzeichnis: cd roommate

Starten Sie die docker-compose.yaml damit der Container geöffnet ist.

Verbinden Sie sich mit der Datenbank und nutzen Sie die Daten von application.yaml um Datenbankzugriff zu bekommen

Führe die Anwendung mit Gradle aus: ./gradlew bootRun