#language: de
  Funktionalität: Inventur

  Als Hausmann möchte ich mein Guthaben und meine Schulden inventarisieren,
  um die Anfangsbestände meines Haushaltsbuches zu kennen

  Szenario: Inventur beginnen
    Wenn ich die Inventur beginne
    Dann wird mein Inventar leer sein

  Szenario: Guthaben während der Inventur erfassen
    Angenommen ich habe mit der Inventur begonnen
    Wenn ich mein Umlaufvermögen "Bankguthaben" in Höhe von "500,00 EUR" erfasse
    Dann werde ich folgendes Vermögen in meinem Inventar gelistet haben
    | Position       | Währungsbetrag  |
    | Bankguthaben   | 500,00 EUR      |

  Szenario: Schulden während der Inventur erfassen
    Angenommen ich habe mit der Inventur begonnen
    Wenn ich  meine Schulden "Autokredit" mit dem Währungsbetrag "10.569,00 EUR" erfasse
    Dann werde ich folgende Schulden in meinem Inventar gelistet haben:
    | Position   | Währungsbetrag |
    | Autokredit | 10.569,00 EUR  |

