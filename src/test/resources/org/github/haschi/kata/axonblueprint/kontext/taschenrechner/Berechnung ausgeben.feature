# language: de
Funktionalität: Berechnung ausgeben
  Als Benutzer eines Taschenrechners mit umgekehrt polnischer Notation
  möchte ich die durchgeführte Berechnung im Display sehen
  um sie kontrollieren zu können

  Grundlage:
    Angenommen ich habe einen Taschenrechner

  Szenario: Berechnung mit einem Operator im Display anzeigen
    Wenn ich die Zahl 1 eingebe
    Und ich die Zahl 2 eingebe
    Und ich addiere
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    1 2 + => 3
    """

  Szenario: Berechnung mit zwei Operatoren im Display anzeigen
    Wenn ich die Zahl 1 eingebe
    Und ich die Zahl 2 eingebe
    Und ich die Zahl 3 eingebe
    Und ich addiere
    Und ich subtrahiere
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    1 2 3 + => 5 - => -4
    """

  Szenario: Berechnung mit Fehler im Display anzeigen
    Wenn ich die Zahl 1 eingebe
    Und ich addiere
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    1 + => ERROR
    """
