# language: de
Funktionalität: Addition
  Als Benutzer eines Taschenrechner mit umgekehrt polnischer Notation
  möchte ich zwei Zahlen addieren
  um deren Summe zu erhalten

  Grundlage:
    Angenommen ich habe einen Taschenrechner

  Szenariogrundriss: : Zwei Zahlen addieren
    Angenommen ich habe die folgenden Zahlen eingeben: <erste Zahl>, <zweite Zahl>
    Wenn ich <operiere>
    Dann werde ich als Ergebnis <Ergebnis> für die Operation <operiere> erhalten haben

    Beispiele:
      | erste Zahl | zweite Zahl | operiere      | Ergebnis |
      | 17         | 13          | addiere       | 30       |
      | 17         | 13          | subtrahiere   | 4        |
      | 30         | 2           | multipliziere | 60       |
      | 30         | 2           | dividiere     | 15       |

  Szenariogrundriss: : Eine Zahl zum Zwischeneregbnis addieren
    Angenommen ich habe ein Ergebnis von <Zwischenergebnis> für die Operation <operiere> berechnet
    Wenn ich die Zahl <Zahl> eingebe
    Und ich <operiere>
    Dann werde ich als Ergebnis <Endergebnis> für die Operation <operiere> erhalten haben

    Beispiele:
      | Zwischenergebnis | Zahl | operiere      | Endergebnis |
      | 30               | 12   | addiere       | 42          |
      | 30               | 12   | subtrahiere   | 18          |
      | 30               | 2    | multipliziere | 60          |
      | 30               | 2    | dividiere     | 15          |

  Szenariogrundriss: : Zu wenig Operatoren eingegeben
    Angenommen ich habe die Zahl 30 eingegeben
    Wenn ich <operiere>
    Dann werde ich einen Fehler erhalten haben

    Beispiele:
      | operiere      |
      | addiere       |
      | subtrahiere   |
      | multipliziere |
      | dividiere     |