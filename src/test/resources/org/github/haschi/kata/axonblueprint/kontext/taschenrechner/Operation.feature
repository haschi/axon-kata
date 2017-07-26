# language: de
Funktionalität: Operation
  Als Benutzer eines Taschenrechner mit umgekehrt polnischer Notation
  möchte ich mit zwei Zahlen eine Operation durchführen
  um ein Berechnungsergebnis zu erhalten

  Grundlage:
    Angenommen ich habe einen Taschenrechner

  Szenariogrundriss: : Operation auf zwei eingegebenen Zahlen anwenden
    Angenommen ich habe die folgenden Zahlen eingeben: <erste Zahl>, <zweite Zahl>
    Wenn ich <operiere>
    Dann werde ich als Ergebnis <Ergebnis> für die Operation <operiere> berechnet haben

    Beispiele:
      | erste Zahl | zweite Zahl | operiere      | Ergebnis |
      | 17         | 13          | addiere       | 30       |
      | 17         | 13          | subtrahiere   | 4        |
      | 30         | 2           | multipliziere | 60       |
      | 30         | 2           | dividiere     | 15       |

  Szenariogrundriss: : Operation auf eine eingegebene Zahl und einem Berechnungsergebnus anwenden
    Angenommen ich habe ein Ergebnis von <Zwischenergebnis> für die Operation <operiere> berechnet
    Wenn ich die Zahl <Zahl> eingebe
    Und ich <operiere>
    Dann werde ich als Ergebnis <Endergebnis> für die Operation <operiere> berechnet haben

    Beispiele:
      | Zwischenergebnis | Zahl | operiere      | Endergebnis |
      | 30               | 12   | addiere       | 42          |
      | 30               | 12   | subtrahiere   | 18          |
      | 30               | 2    | multipliziere | 60          |
      | 30               | 2    | dividiere     | 15          |

  Szenariogrundriss: : Operation auf eine einzelne eingegebene Zahl anwenden
    Angenommen ich habe die Zahl 30 eingegeben
    Wenn ich <operiere>
    Dann werde ich einen Fehler gemacht haben

    Beispiele:
      | operiere      |
      | addiere       |
      | subtrahiere   |
      | multipliziere |
      | dividiere     |