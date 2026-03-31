# 登录页面说明

这是一个使用Vue 3构建的简单登录页面，采用CDN方式引入Vue，无需构建工具。

## 功能特性

- 用户名和密码输入
- 表单验证
- 登录状态反馈
- 响应式设计
- 现代化UI样式

## 使用方法

1. 直接在浏览器中打开 [index.html](./index.html) 文件
2. 输入用户名：`admin`
3. 输入密码：`123456`
4. 点击登录按钮进行登录

## 技术栈

- Vue 3 (通过CDN)
- HTML5
- CSS3

## 项目结构

```
frontend/
├── index.html      # 主页面
├── style.css       # 样式文件
└── README.md       # 说明文档
```

## 自定义配置

如需连接到实际后端API，可以在 [index.html](./index.html) 文件中的 `handleLogin` 函数中修改登录逻辑。