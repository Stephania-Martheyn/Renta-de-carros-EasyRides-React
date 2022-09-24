import React from "react";
import "./ReservaExitosa.css";
import checked from "./checked-car.png";
import { Link } from "react-router-dom";

function ReservaExitosa() {

    return (
        <div className="contenedor">

            <div className="card">
                <img className="icon-checked" src={checked} alt="Imagen card checked"></img>

                <h1>¡Muchas gracias!</h1>
                <p>Su reserva se ha realizado con éxito</p>

                <Link to="/">
                    <button className="boton">OK</button>
                </Link>

            </div>
        </div>
    );
}

export default ReservaExitosa;
