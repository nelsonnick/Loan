<template>
  <div class="layout">
    <Layout>
      <Header>
        <MenuTop/>
      </Header>
      <Content>
        <router-view/>
      </Content>
    </Layout>
    <Footer class="layout-footer-center">2020 &copy; It's Show Time!</Footer>
  </div>
</template>
<script>
import MenuTop from './components/Common/MenuTop.vue'
export default {
  name: 'app',
  components: {
    MenuTop
  }
}
</script>
<style lang="less">
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
