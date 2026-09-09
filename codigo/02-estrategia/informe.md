# Informe

| Version | Archivos creados | Archivos modificados | ¿Se modifico el archivo que calcula la plata? |
| --- | ---: | ---: | --- |
| Version con condicionales | 0 | 2 | Si |
| Version con estrategias | 1 | 2 | No |

La version con estrategias costo menos en lo importante: el archivo que calcula la plata no se toco. En la version con condicionales si hubo que cambiar `LiquidacionService.java`, que es donde vive la logica de negocio, y tambien `Main1.java` como dato de prueba. En la version con estrategias el cambio nuevo quedo en `PoliticaOrganico.java` y el cableado solo registro esa politica, asi que el calculo principal no cambio.

El archivo que se modifico ademas del `Main1` fue `Politicas.java`. Ahí vive el conocimiento de que politicas existen y como se busca cada una por tipo. Cambiar ese archivo es menos riesgoso que cambiar el calculo, porque solo agrega una entrada al registro y no altera las formulas de liquidacion de los demas cafes.

Strategy seria sobre-ingenieria si este problema solo cambiara por una constante. Por ejemplo, si organico fuera exactamente pergamino con una prima fija de 0,06 y nunca hubiera mas reglas distintas, un `if` simple o incluso una tabla de constantes bastaria. En ese caso el costo de crear clases nuevas no se recupera.

El redisenio ayuda poco o nada para el cambio de recalcular con el precio del dia anterior despues de las 6 p.m. Aqui el cambio no es de formula de calidad sino de precio, asi que la parte que tendria que cambiar es otra. Lo bueno es que la separacion actual deja la formula de calidad aislada, pero aun asi habria que tocar el servicio de liquidacion o agregar otro componente para decidir que precio usar.
