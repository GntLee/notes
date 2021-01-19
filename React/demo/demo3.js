import React from 'react';
import ReactDOM from 'react-dom';

const element = document.querySelector('#root');
// -------------------
export const themes = {
    light: {
      foreground: '#000000',
      background: '#eeeeee',
    },
    dark: {
      foreground: '#ffffff',
      background: '#222222',
    },
};
const ThemContext = React.createContext(themes.dark);

class ThemeButton extends React.Component {
    // ����context
    static contextType = ThemContext;
    render(){
        let proprs = this.props;
        let themes = this.context;
        return (
            // ��context�ж�ȡ����
            <button {...proprs} style={{backgroundColor: themes.background}}></button>
        )
    }
}

class Toolbar extends React.Component {
    render (){
        return (
            <ThemeButton onClick={this.props.changeTheme}> Change Theme</ThemeButton>
        )
    }
}

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            themes: themes.light
        }
    }
    changeTheme = () => {
        this.setState((state, proprs) => {
            return {
                themes: state.themes === themes.light
                    ? themes.dark
                    : themes.light
            }
        })
    }
    render (){
        return (
           <div>
               {/* ����Context Provide, ��дĬ��ֵ, ����ʹ�õ�ǰ����� state ���� */}
               <ThemContext.Provider value={this.state.themes}>
                    <Toolbar changeTheme={this.changeTheme}></Toolbar>
               </ThemContext.Provider>
           </div>
        )
    }
}

  

ReactDOM.render(<App />, element);