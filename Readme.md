Axon Kata
=========

Eine Projektvorlage für Katas, die das [Axon Framework](http://www.axonframework.org) verwenden.

Benötigt:
---------
* Java 1.8
* Maven 3.3.9

Bietet:
-------
* Ein einfaches Beispiel zum ausprogrammieren anhand vorhandener Spezifikation
* Testwerkzeuge und Frameworks
  * [Cucumber-JVM](https://cucumber.io/docs/reference/jvm) ([Picocontainer](http://picocontainer.com/) für DI)
  * [JUnit 4.12](http://junit.org/junit4/)
  * [EqualsVerifier](http://jqno.nl/equalsverifier/)
  * Lorem Ipsum Generator
  * [AssertJ](http://joel-costigliola.github.io/assertj/)
* [Javaslang](http::/www.javaslang.io)
* [Immutables](https://immutables.github.io/) für
  * Anweisung
  * Ereignis
  * Information
  * Abfrage
  * Abgegrenzter Kontext

Anwendung
---------

Projekt forken bzw. klonen und branchen. Los geht's!

Die Features sollten in folgender Reihenfolge implementiert werden:

1. Zahl eingeben
2. Operation ausführen
3. Berechnung ausgeben

Im Package taschenrechner.abfrage befindet sich ein rudimentäres Lesemodell Display.
Die Schreibseite wird durch ein Aggregat Taschenrechner im Package taschenrechner.domaene
realisiert. Anweisungen, Ereignisse und Wertobjekte befinden sich im Package
taschenrechner.api.

Die getrennte Infrastruktur für CQRS wird im Test als Anweisungskonfiguration (Schreibseite)
und Abfragekonfiguration (Leseseite) realisiert. Schreib- und Leseseite verwenden einen
gemeinsamen EventStore. Die Infrastruktur ist vorkonfiguriert und ist über Dependency
Injection in den Steps verfügbar.

Fehler und Anmerkungen bitte in [Github als Issue](https://github.com/haschi/axon-kata/issues)
beschreiben.

## License

This software is licensed under the Apache 2 license, quoted below.

Copyright 2016 Matthias Haschka

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

