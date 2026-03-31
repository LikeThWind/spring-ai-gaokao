// 登录页面的Vue 3组件
const { createApp, ref } = Vue;

// 模拟API请求函数
const loginAPI = async (username, password) => {
    // 这里可以替换成实际的API调用
    // return await fetch('/api/login', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({ username, password })
    // }).then(res => res.json());
    
    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 500));
    
    // 模拟登录验证
    if (username === 'admin' && password === '123456') {
        return { success: true, message: '登录成功', user: { id: 1, name: username } };
    } else {
        throw new Error('用户名或密码错误');
    }
};

// 创建Vue应用
const app = createApp({
    setup() {
        const username = ref('');
        const password = ref('');
        const errorMessage = ref('');
        const successMessage = ref('');
        const loading = ref(false);
        
        const handleLogin = async () => {
            // 清除之前的消息
            errorMessage.value = '';
            successMessage.value = '';
            
            // 简单验证
            if (!username.value.trim()) {
                errorMessage.value = '请输入用户名';
                return;
            }
            
            if (!password.value) {
                errorMessage.value = '请输入密码';
                return;
            }
            
            loading.value = true;
            
            try {
                // 调用登录API
                const response = await loginAPI(username.value, password.value);
                
                if (response.success) {
                    successMessage.value = response.message || '登录成功！';
                    console.log('登录成功', { user: response.user, username: username.value });
                    
                    // 这里可以进行页面跳转等操作
                    // window.location.href = '/dashboard';
                }
            } catch (error) {
                errorMessage.value = error.message || '登录失败，请重试';
                console.error('登录错误:', error);
            } finally {
                loading.value = false;
            }
        };
        
        // 重置表单
        const resetForm = () => {
            username.value = '';
            password.value = '';
            errorMessage.value = '';
            successMessage.value = '';
        };
        
        return {
            username,
            password,
            errorMessage,
            successMessage,
            loading,
            handleLogin,
            resetForm
        };
    }
});

// 挂载应用 - 等待DOM加载完成
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', () => {
        app.mount('#app');
    });
} else {
    app.mount('#app');
}