# Voraussetzungen
- Java 15.0.2
- references.csv

# Ausführung
## Definition
```java -jar lab-estimate.jar /pfad/zu/references.csv <L> <a> <b> [verbose]```

## Beispiel
```
$ java -jar lab-estimate.jar references.csv 55.83 -15.94 29.05
LAB{l=50.590000, a=-18.870000, b=35.460000}
```

```
$ java -jar lab-estimate.jar references.csv 55.83 -15.94 29.05 verbose

Input:
 > LAB: LAB{l=55.830000, a=-15.940000, b=29.050000}
 > Reference White: StandardIlluminant{name=D50, xy=XY{x=0.347730, y=0.359520}, cct=5003}

 > Source:
    > LAB: LAB{l=55.830000, a=-15.940000, b=29.050000}
    > XYZ: XYZ{x=0.195973, y=0.237434, z=0.086704}

 > Target:
    > LAB: LAB{l=50.590000, a=-18.870000, b=35.460000}
    > XYZ: XYZ{x=0.149201, y=0.189170, z=0.050855}

 > Delta E76 (Input vs. Reference source)
    > 0.0

 > Delta E76 (Reference source vs. Reference target)
    > 8.782402860265519

 > Estimated Delta E76
    > 1.7763568394002505E-15

 > Estimated LAB
LAB{l=50.590000, a=-18.870000, b=35.460000}

```
