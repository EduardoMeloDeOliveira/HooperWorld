import React from 'react';
import Slider from 'react-slick';
import ReactTypingEffect from 'react-typing-effect';

function Home() {
    const settings = {
        dots: false,
        infinite: true,
        speed: 450,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    return (
        <div className="d-flex justify-content-center align-items-center min-vh-100">
            <div className="container p-4 h-100">
                <Slider {...settings}>
                    <div className="d-flex justify-content-center align-items-center p-5 bg-primary h-75">
                        <h3 className="text-white">

                            <ReactTypingEffect
                                text={"bem vindo ao Hoop.co"}
                                speed={150}
                                eraseDelay={10500}
                                typingDelay={100}
                            />

                        </h3>
                    </div>
                    <div className="d-flex justify-content-center align-items-center p-5 bg-black h-75">
                        <h3 className="text-white"> <ReactTypingEffect
                            text={"bem vindo ao Hoop.co"}
                            speed={150}
                            eraseDelay={10500}
                            typingDelay={100}
                        /></h3>
                    </div>
                    <div className="d-flex justify-content-center align-items-center p-5 bg-danger h-75">
                        <h3 className="text-white"> <ReactTypingEffect
                            text={"bem vindo ao Hoop.co"}
                            speed={100}
                            eraseDelay={3000}
                            typingDelay={0}
                        /></h3>
                    </div>
                </Slider>
            </div>
        </div>
    );
}

export default Home;
