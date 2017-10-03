#language: de
Funktionalität: Haushaltsbuchführung beginnen

  Als Hausmann möchte ich mit der Haushaltsbuchführung beginnen damit
  ich meine laufenden Einnahmen und Ausgabe verbuchen kann

  Szenario: Haushaltsbuchführung beginnen
    Angenommen ich habe mit der Inventur begonnen
    Angenommen ich habe folgendes Inventar erfasst:
      | Gruppe   | Untergruppe           | Position         | Währungsbetrag |
      | Vermögen | Anlagevermögen        | Sparbuchguthaben | 5.300,00 EUR   |
      | Vermögen | Umlaufvermögen        | Bankguthaben     | 3.567,00 EUR   |
      | Vermögen | Umlaufvermögen        | Geldbörse        | 130,89 EUR     |
      | Schulden | Langfristige Schulden | Autokredit       | 10.567,00 EUR  |
    Wenn ich die Haushaltsbuchführung beginne
    Dann werde ich folgendes Eröffnungsbilanzkonto im Hauptbuch erstellt haben:
      | Soll                      | Haben                       |
      | Eigenkapital 1.000,00 EUR | Anlagevermögen 500,00 EUR   |
      | Fremdkapital 1.000,00 EUR | Umlaufvermögen 1.000,00 EUR |