# 仿支付宝密码键盘

## 使用
```
  PayPopWindow payPopWindow = new PayPopWindow(MainActivity.this);
                payPopWindow.setOnPasswordEditListener(new PayPopWindow.OnPasswordEditListener() {
                    @Override
                    public void onEditFinish(String password) {
                        //在此校验输入的密码
                        Toast.makeText(MainActivity.this, "输入完成", Toast.LENGTH_SHORT).show();

                    }
                });
  payPopWindow.show();
```