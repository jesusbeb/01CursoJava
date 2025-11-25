package org.example.contenido;

//Creamos una interface para hacer contenido Promocionable sin tener que crear
//la estructura dentro de cada tipo de Contenido, sino hacerlo mas general para
//luego agregar mas contenido que queramos promocionar.
public interface Promocionable {

    //Metodo promocionar que retorna un String
    String promocionar();
}



/*
Las interfaces permiten definir un conjunto de métodos que varias clases pueden implementar,
independientemente de su jerarquía. Así puedes crear estructuras más versátiles, listas para
futuras expansiones sin modificar el código existente.

- Las interfaces se declaran usando la palabra reservada interface después del modificador de acceso.
- Dentro de una interfaz, solo se definen los métodos, sin implementar su lógica (a menos que sea default o static, pero en este caso no se utiliza default).
- Los métodos de una interfaz actúan como contratos: cualquier clase que implemente la interfaz debe definir la lógica de esos métodos.

¿Por qué usar interfaces mejora la arquitectura del software?

Las interfaces facilitan que el sistema pueda crecer sin complicaciones ni dependencias excesivas entre clases, y este patrón también sirve fuera del ejemplo de documentales.

¿Qué ventajas tienen frente a otros enfoques?

- Menor acoplamiento: Evitas atar la lógica a tipos concretos, permitiendo más flexibilidad.
- Extensibilidad: Nuevas funcionalidades (por ejemplo, más servicios de pago) pueden agregarse sin alterar lo ya implementado.
- Dependencia de abstracciones: Sigues el principio SOLID de invertir dependencias, donde el
sistema depende de contratos (interfaces) en vez de implementaciones específicas.

¿Cómo se aplica este patrón a servicios reales?

Pensemos en un sistema de pagos que procesa servicios como Stripe, PayPal o tarjetas de crédito. Todos pueden implementar una
misma interfaz llamada ServicioDePagoEnLinea con un método procesarPago.

Esto permite que el sistema procese pagos sin preocuparse por los detalles de cada proveedor. Solo invoca procesarPago y deja a
la clase concreta encargarse de la lógica interna.

¿Cómo identificar cuándo crear una interfaz en tu proyecto Java?

Te conviene usar una interfaz cuando:

- Múltiples clases comparten una funcionalidad, pero no tienen relación de herencia directa.
- Buscas mantener bajo el acoplamiento para facilitar cambios y extensiones futuras.
- Quieres apoyar decisiones de diseño como las que imponen los principios SOLID.

*/