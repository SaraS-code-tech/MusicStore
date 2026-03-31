import { defineConfig } from "vite";

export default defineConfig({
  build: {
    manifest: true,
    outDir: "src/main/resources/static",
    emptyOutDir: true,
    rollupOptions: {
      input: "src/main/resources/assets/main.js",
      output: {
        entryFileNames: `assets/[name].js`,
        chunkFileNames: `assets/[name].js`,
        assetFileNames: (assetInfo) => {
          if (assetInfo.name && assetInfo.name.endsWith('.css')) {
            return 'style.css';
          }
          return `[name].[ext]`;
        }
      }
    }
  }
});
