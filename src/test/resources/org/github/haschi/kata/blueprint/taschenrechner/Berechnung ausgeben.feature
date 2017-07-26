# language: de
Funktionalität: Berechnung ausgeben
  Als Benutzer eines Taschenrechners mit umgekehrt polnischer Notation
  möchte ich die durchgeführte Berechnung im Display sehen

  Grundlage:
    Angenommen ich habe einen Taschenrechner

  Szenario: Eingegebene Zahlen im Display anzeigen
    Wenn ich folgende Zahlen eingebe: 1, 2, 3
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    1 2 3
    """

  Szenario: Berechnung mit einem Operator im Display anzeigen
    Wenn ich folgende Zahlen eingebe: 1, 2
    Und ich addiere
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    1 2 + => 3
    """

  Szenariogrundriss: : Berechnung mit zwei Operatoren im Display anzeigen
    Wenn ich folgende Zahlen eingebe: <Eingabe>
    Und ich <operiere 1>
    Und ich <operiere 2>
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    <Displayanzeige>
    """

    Beispiele:
      | Eingabe    | operiere 1    | operiere 2  | Displayanzeige          |
      | 1, 2, 3    | addiere       | subtrahiere | 1 2 3 + => 5 - => -4    |
      | 100, 25, 2 | multipliziere | dividiere   | 100 25 2 * => 50 / => 2 |

  Szenariogrundriss: : Berechnung mit Fehler im Display anzeigen
    Wenn ich <Eingabe> eingebe
    Und ich <operiere>
    Dann werde ich im Display folgende Ausgabe sehen:
    """
    <Displayanzeige>
    """
    Beispiele:
      | Eingabe    | operiere    | Displayanzeige |
      | die Zahl 1 | addiere     | 1 + => ERROR   |
      | keine Zahl | subtrahiere | - => ERROR     |