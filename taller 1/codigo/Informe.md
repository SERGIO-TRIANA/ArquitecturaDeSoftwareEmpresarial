# Informe comparativo: condicionales vs. Strategy

| Versión | Archivos creados | Archivos modificados | ¿Se modificó el archivo que calcula la plata? |
| --- | ---: | ---: | --- |
| Versión con condicionales | 0 | 2 | Sí |
| Versión con estrategias | 1 | 2 | No |

## 1. Comparación del costo de cambio

La versión con estrategias costó menos cuando se mide lo importante: no se tocó el archivo que calcula la 
liquidación. En la versión con condicionales sí se cambió LiquidacionService.java, que concentra la lógica de 
negocio y además se actualizó Main.java para incluir la muestra de prueba del nuevo café. La diferencia no es 
solo de cantidad de archivos, sino de qué archivos se alteran: cambiar la lógica de cálculo es más costoso que 
conectar una nueva política. En la versión con estrategias, el cambio quedó más aislado: se creó 
PoliticaOrganico.java y se registró desde Politicas.java, sin tocar la fórmula central.

## 2. Lógica de negocio y cableado

La primera pregunta del taller pide distinguir entre lógica de negocio y cableado. En la versión con condicionales, 
el cálculo de la plata está dentro del mismo archivo que decide el tipo del café, así que el cambio obligó a 
modificar un archivo crítico. En la versión con estrategias, el archivo que calcula la liquidación no se tocó, 
porque la nueva política se encapsuló en su propia clase y el registro se hizo en Politicas.java. Esa distinción 
importa porque un cambio en la lógica del negocio puede introducir errores de cálculo, mientras que un cambio en 
el registro de políticas suele ser más seguro y más localizado. Esa es la razón por la que, al medir costo, el 
diseño con estrategias resultó más barato en términos de riesgo y de impacto.

## 3. Riesgo de modificar Politicas.java

La segunda pregunta también tiene que ver con el archivo que se modificó además del Main. Ese archivo fue 
Politicas.java, y ahí vive el conocimiento de qué políticas existen y cómo se relacionan con cada tipo de 
café. Ese tipo de conocimiento es menos riesgoso que tocar la lógica de liquidación porque no cambia la fórmula, 
sino que solo agrega una entrada al mapa que resuelve el tipo. Si algo falla allí, el problema será de 
registro o de identificación del tipo, no de cálculo de dinero. En cambio, si se modifica el archivo que 
calcula la plata, se puede romper el comportamiento de todas las liquidaciones aunque el cambio sea pequeño. 
Por eso modificar Politicas.java es mucho menos peligroso que tocar LiquidacionService.java.

## 4. Cuándo Strategy sería sobre-ingeniería

Una situación en la que aplicar Strategy sería sobre-ingeniería sería si la cooperativa solo comprara un 
tipo de café, por ejemplo solo PERGAMINO, y la fórmula no cambiara según el tipo ni según la certificación. 
En ese caso, una simple condición o incluso una constante en un único método bastaría, y crear una interfaz y 
varias clases sería más trabajo que valor. Otro ejemplo concreto sería una operación puntual para un pedido único, 
donde se sabe que solo existe una política y no va a crecer. En ese escenario, la versión con condicionales 
sería la decisión correcta porque el costo de abstraer no se justifica. El curso busca entender que Strategy 
no es “siempre mejor”, sino “útil cuando hay variedad real y cambio de comportamiento”.

## 5. Alcance del diseño ante una nueva regla temporal

La cuarta pregunta dice que la cooperativa quiere recalcular el factor de calidad con los precios del 
gremio del día anterior cuando la entrega llega después de las 6 p.m. Ese cambio no está relacionado 
con la variedad de tipos de café ni con la fórmula de calidad; está relacionado con la fuente del precio 
y con la regla temporal. El rediseño de la sesión 2 ayuda poco o nada aquí, porque Strategy aisló la lógica 
de calidad, pero no resuelve cómo se decide el precio del día anterior ni cómo se aplica la regla de horario. 
En otras palabras, la arquitectura ayuda para la clase de cambio que se pensó para ella, pero no para todos 
los cambios posibles. Reconocer ese límite es parte importante del trabajo: un diseño no es una solución 
universal, sino una decisión con costo y alcance definidos.

## Conclusión

En resumen, la medición del taller muestra que la versión con condicionales costó más porque tocó la 
lógica central del cálculo y la duplicó dentro del mismo método. La versión con estrategias costó 
menos porque el cambio se encapsuló en una política nueva y en el registro de dicha política. 
Esa diferencia no es solo de estilo: representa una variación real en el costo del cambio, el 
riesgo de error y la facilidad para evolucionar el sistema.