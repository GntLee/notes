import React from 'react';
import ReactDOM from 'react-dom';

const ROOT = document.querySelector('#root');

// �Ƿ�����ж����
function BoilingVerdict (props){
    if (props.celsius >= 100){
        return <p>ˮ�����</p>;
    }
    return <p>ˮ�������</p>;
}

// �¶ȵ�����
const scaleNames = {
    c: '���϶�',
    f: '���϶�'
};

// ���϶�ת��Ϊ���϶�
function toCelsius(fahrenheit) {
    return (fahrenheit - 32) * 5 / 9;
}

// ���϶�ת��Ϊ���϶�
function toFahrenheit(celsius) {
    return (celsius * 9 / 5) + 32;
}

// ������ temperature ��ֵ��Чʱ���������ؿ��ַ�������֮���򷵻ر�����λС��������������ת�����
function tryConvert(temperature, convert) {
    const input = parseFloat(temperature);
    if (Number.isNaN(input)) {
      return '';
    }
    const output = convert(input);
    const rounded = Math.round(output * 1000) / 1000;
    return rounded.toString();
}


// �¶���ʾ���
class TemperatureInput extends React.Component {
    handleChange = (e) => {
        this.props.onTemperatureChange(e.target.value);
    }
    render (){
        // �¶�
        const temperature = this.props.temperature;
        // �¶�����
        const scale = this.props.scale;
        return (
            <fieldset>
                <legend>����{scaleNames[scale]}�¶�:</legend>
                <input value={temperature} onChange={this.handleChange}/>
            </fieldset>
        );
    }
}

class Calculator extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            // �¶�
            temperature: '37',
            // �¶�����
            scale: 'c'
        }
    }

    // ���϶ȱ��޸���
    handleCelsiusChange = (temperature) => {
        this.setState({scale: 'c', temperature});
    }
    // ���϶ȱ��޸���
    handleFahrenheitChange = (temperature) => {
        this.setState({scale: 'f', temperature});
    }
   
    render (){
        // �¶�
        const temperature = this.state.temperature;
        // �¶�����
        const scale = this.state.scale;
        // ��������϶�
        const celsius = scale === 'f' ? tryConvert(temperature, toCelsius) : temperature;
        // ��������϶�
        const fahrenheit = scale === 'c' ? tryConvert(temperature, toFahrenheit) : temperature;

        return (
            <div>
                <TemperatureInput 
                    scale="c"
                    temperature={celsius}
                    onTemperatureChange={this.handleCelsiusChange}
                    />
                <TemperatureInput scale="f"
                    temperature={fahrenheit}
                    onTemperatureChange={this.handleFahrenheitChange}
                    />
                <BoilingVerdict celsius={parseFloat(celsius)} />
            </div>
        );
    }
}

ReactDOM.render(<Calculator></Calculator>, ROOT);
