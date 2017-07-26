# language: de
Funktionalität: Berechnung ausgeben
  Als Benutzer eines Taschenrechners mit umgekehrt polnischer Notation
  möchte ich die durchgeführte Berechnung sehen
  um sie kontrollieren zu können

  Grundlage:
    Angenommen ich habe einen Taschenrechner

  Szenario: Berechnung mit einem Operator
    Angenommen ich habe die Zahl 1 eingegeben
    Angenommen ich habe die Zahl 2 eingegeben
    Angenommen ich addiere
    Wenn ich die Berechnung ansehe
    Dann werde ich folgende Ausgabe erhalten:
    """
    1 2 + => 3
    """

  Szenario: Berechnung mit mehreren Operatoren
    Angenommen ich habe die Zahl 1 eingegeben
    Angenommen ich habe die Zahl 2 eingegeben
    Angenommen ich habe die Zahl 3 eingegeben
    Angenommen ich addiere
    Angenommen ich subtrahiere
    Wenn ich die Berechnung ansehe
    Dann werde ich folgende Ausgabe erhalten:
    """
    1 2 3 + => 5 - => -4
    """
  Szenario: Eingabe eines Operators mit zuwenigen Operatoren
    Angenommen ich habe die Zahl 1 eingegeben
    Angenommen ich addiere
    Dann werde ich folgende Ausgabe erhalten:
    """
    1 + => ERROR
    """
