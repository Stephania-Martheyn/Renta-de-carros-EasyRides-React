import React from 'react'
import { useParams } from 'react-router';
import useFetch from '../../../../Hooks/useFetch';

const FiltroReservasRealizadas = () => {
    const urlReservas = "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/reservas";
    const { data } = useFetch(urlReservas);
    const { id } = useParams();
    
    // fetch de productos por id me tiene que traer los dias no disponibles de ese producto

  return (
    <div>
      {data &&
        data.reservas.map((prod) => (
          <>
            {/* {console.log(prod.producto.id)} */}
            {prod.producto.id == id ? (
              <div key={prod.id}>
                {console.log([prod.fechaInicial,
                prod.fechaFinal])}
              </div>
            ) : (
              console.log("noanda")
            )}
          </>
        ))}
    </div>
  );
}

export default FiltroReservasRealizadas