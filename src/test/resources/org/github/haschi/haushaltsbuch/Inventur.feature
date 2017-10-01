#language: de
Funktionalität: Inventur

  Als Hausmann möchte ich mein Guthaben und meine Schulden inventarisieren,
  um die Anfangsbestände meines Haushaltsbuches zu kennen

# http://www.it-infothek.de/wirtschaftsinformatik/semester-2/externes-rechnungswesen-03.html

  Szenario: Inventur beginnen
    Wenn ich die Inventur beginne
    Dann wird mein Inventar leer sein

  Szenario: Guthaben während der Inventur erfassen
    Angenommen ich habe mit der Inventur begonnen
    Wenn ich mein Umlaufvermögen "Bankguthaben" in Höhe von "500,00 EUR" erfasse
    Dann werde ich folgende Vermögenswerte in meinem Inventar gelistet haben:
      | Position     | Währungsbetrag |
      | Bankguthaben | 500,00 EUR     |

  Szenario: Schulden während der Inventur erfassen
    Angenommen ich habe mit der Inventur begonnen
    Wenn ich  meine Schulden "Autokredit" in Höhe von "10.569,00 EUR" erfasse
    Dann werde ich folgende Schulden in meinem Inventar gelistet haben:
      | Position   | Währungsbetrag |
      | Autokredit | 10.569,00 EUR  |

  Szenario: Inventar erfassen
    Angenommen ich habe mit der Inventur begonnen
    Wenn ich folgendes Inventar erfasse:
      | Gruppe   | Untergruppe           | Position     | Währungsbetrag |
      | Vermögen | Umlaufvermögen        | Bankguthaben | 500,00 EUR     |
      | Schulden | Langfristige Schulden | Autokredit   | 10.569,00 EUR  |
    Dann werde ich folgendes Eigenkapital ermittelt haben:
      | Summe des Vermögens         | 500,00 EUR     |
      | Summe der Schulden          | 10.569 EUR     |
      | Reinvermögen (Eigenkapital) | -10.069,00 EUR |


