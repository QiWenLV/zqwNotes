### 创建项目

保证有node环境

```sh
node -v
npm -v
```

使用脚手架创建项目(todolist为项目名)

```sh
npx create-react-app todolist
```

启动项目

```sh
cd todolist
npm start
```



### 组件

拆分页面，页面的组成零部件，就是组件

在React中，大写字母开头的都是组件

```js
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

// App组件
import App from './App';

ReactDOM.render(<App />, document.getElementById('root'));
```

ReactDOM 组件会将App组件渲染到root div中。

---

最简单的组件

```js
class App extends React.Component {
  render() {
    return (
      <div className="App">
        hello, i am ...
      </div>
    );
  }
}
export default App;
```

组件必须继承` React.Component`类，必须实现`render()`方法

#### 组件之间的通信

父组件通过参数传递数据

```js
this.state.list.map((item, index) => {
    return <TodoItem content={item}/>
})
```

子组件通过属性接收

```js
 render() {
     return(
         <div>{this.props.content}</div>
     )
 }
```

---

子组件向父组件传递数据是通过接口回调的方式

父组件通过属性传递接口实例给子组件

```js
this.state.list.map((item, index) => {
    return <TodoItem delete={this.handleDelete.bind(this)} key={index} content={item} index={index}/>
})

//父组件实现接口方法
handleDelete(index){
    const list = [...this.state.list];
    list.splice(index, 1);
    this.setState({
        list
    })
}
```

子组件调用接口方法

```js
handleDelete() {
	this.props.delete(this.props.index);
}
```



### JSX语法

在js中直接使用 html标签，就是JSX语法。如下：

```js
render() {
    return (
        <div className="App">
        {1 + 2}
        hello, i am ...
        </div>
    );
}
```

jSX中可以写**JS的表达式**（不能写逻辑代码）



