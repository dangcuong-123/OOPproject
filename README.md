# OOP Project

## A. Giới thiệu
Đây là bài tập lớn hướng đối tượng.

**I. Mô tả:**
Mỗi camera có một tầm nhìn giới hạn (bởi góc nhìn của camera và tầm xa của nó) cũng như trong phòng (có chứa camera) đặt các vật thể cản mất tầm nhìn của camera.

![image](https://user-images.githubusercontent.com/48614539/120887325-5016fd80-c61c-11eb-84f5-e54f77e3557d.png)

Trong tình huống này, phát sinh ra các bài toán sau:

- Tính ra được các vùng khuất của một camera khi đã được cung cấp thông tin về vị trí đặt camera (trong không gian 3D), góc cao và góc rộng của camera. Vùng khuất này vẫn có tính đến các vị trí nằm trong tầm nhìn của camera nhưng bị che bởi các vật thể khác. Bài toán này có thể mở rộng cho việc có nhiều camera ở bên trong phòng
- Với một camera cho trước, xác định cần phải đặt thêm tối thiểu là bao nhiêu camera nữa (và đặt ở đâu) để không còn góc khuất nào.
- với số lượng camera bị chặn trên, hãy xác định các vị trí đặt camera sao cho diện tích (thể tích thì đúng hơn) phần góc khuất là bé nhất

**II. Giả thuyết để đơn giản hóa bài toán**

Ta có các giả thuyết sau để đơn giản hóa bài toán đi:

- Căn phòng luôn là hình hộp chữ nhật và có cạnh song song với trục tọa độ
- Camera không tự động xoay được (một khi đã được gắn lên tường là không thể cử động được gì)
- Các đồ vật trong phòng luôn là hình hộp chữ nhật và luôn đặt thẳng đứng (tức vuông góc với sàn phòng). 
- Căn phòng luôn có đủ độ sáng
- Các đồ vật luôn cố định
- Camera chỉ đặt vuông góc với tường 
- Kích thước camera không đáng kể, coi nó như là một điểm gắn trên tường hoặc trần

![image](https://user-images.githubusercontent.com/48614539/120887346-6cb33580-c61c-11eb-86bb-d6eb41c0ac29.png)

**III. Định dạng file đầu vào:**

 **Input:**
 
- Dòng đầu tiên là toạ độ 8 điểm (x, y, z) của hình hộp chữ nhật biểu diễn căn phòng
  - Một điểm nằm ở gốc (0, 0, 0)
  - Các giá trị dương
  - Cạnh song song với gốc toạ độ
  - Các điểm cách nhau bởi dấu cách
  - Giá trị x, y, z được phân chia bởi dấu phẩy
  - Giá trị x, y, z nằm trong một ngoặc đơn
- Dòng tiếp theo là số tự nhiên n (n >= 0) là số vật thể trong phòng
  - N dòng tiếp theo, mỗi dòng là 8 điểm biểu diễn hình hộp chữ nhật của vật thể (format tương tự như trên)
  - Các vật thể không giao nhau và không cắt với các mặt phẳng của tường phòng
- Dòng tiếp theo là số tự nhiên m (m >= 0) là số camera trong phòng
  - M dòng tiếp theo mỗi dòng là toạ độ đặt camera, góc rộng camera, góc cao camera
  - Camera luôn nằm trên tường và có phương vuông góc với tường
  - Vùng nhìn thấy của camera là hình chóp chữ nhật đều
  - Góc quay của camera có đáy (là hình chữ nhật song song với các trục toạ độ)
  - Góc rộng và góc cao là góc giữa hai mặt phẳng cạnh bên của hình chóp

**IV. Các chức năng:**
Chương trình có các chức năng sau:

- **Chức năng lập phòng:** người dùng đọc từ file vào kích thước của phòng, tọa độ của các vật thể trong phòng (do vật hình hộp chữ nhật nên chỉ cần nhập vào tọa độ của 2 đỉnh đối diện của HCN tiếp xúc sàn, chiều dài một cạnh của HCN đó, kiêm chiều cao của vật thể), màu sắc của vật thể. Chương trình sẽ phải kiểm tra tính hợp lệ của: kích thước vật thể (phải là hình hộp chữ nhật), vị trí đặt vật thể (cụ thể hơn mọi vật thể đều phải nằm trong phòng), nếu vật thể không tiếp xúc sàn thì nó phải nằm trên một vật thể khác.

- **Chức năng đặt camera:** người dùng đọc từ file vào tọa độ của camera, góc cao, góc rộng camera. Chương trình phải kiểm tra xem tọa độ của camera có nằm trong phòng (và trên tường hoặc trên trần không), góc nhìn của camera phải bé hơn 90 độ, tầm xa dưới 100m. 

- **Chức năng tính toán vùng khuất:** Chương trình phải tính toán xem một điểm bất kỳ trong phòng sẽ thuộc nhóm vật thể nào? Cụ thể hơn, nó có thể thuộc loại: 
  - Điểm thuộc vật thể (trong lòng hoặc trên bề mặt)
  - Điểm không thuộc vật thể và nằm trong vùng nhìn được của một camera nào đó (nếu phòng có nhiều hơn một camera)
  - Điểm không thuộc vật thể và không nằm trong vùng nhìn được của bất kỳ camera nào

- **Chức năng hiển thị vùng khuất:**

Chương trình hiển thị năm hình chiếu của vùng khuất trong phòng, các vùng khuất sẽ có màu tối đen. Năm hình chiếu ứng với 5 hướng nhìn khác nhau vào phòng: trên xuống, trái sang phải, phải sang trái, trước sang sau, sau sang trước.

- **Chức năng xác định cần phải đặt thêm tối thiểu là bao nhiêu camera nữa (và đặt ở đâu) để không còn góc khuất nào**

- **Chức năng xác định các vị trí đặt camera sao cho diện tích (thể tích thì đúng hơn) phần góc khuất là bé nhất **


## B. Giao Diện
### I. Giao diện ứng dụng 

![191778538_932930043947053_4227792987921249349_n](https://user-images.githubusercontent.com/48614539/120886396-eb59a400-c617-11eb-965e-9aeed82c539c.png)

### II. Màn hình chức năng Read File

![192650981_1954896618001185_641201620609894395_n](https://user-images.githubusercontent.com/48614539/120886447-2c51b880-c618-11eb-8a43-1eff085f7323.png)

- Tại đây bạn chọn đường dẫn đến file .txt là file intput của chương trình

- Sau khi chọn được file input bạn sẽ thực hiện được các chức năng ở dưới 

### III. Màn hình chức năng Check a Point 

![191097420_323110452714765_4812169849986742422_n](https://user-images.githubusercontent.com/48614539/120886510-94a09a00-c618-11eb-919b-c1acbca7bfc3.png)

- Tại đây bạn nhập tọa đọ 1 điểm và kiểm tra điểm đó là sáng hay tối 

- Màn hình sẽ thông báo như này 

![197288236_314634740280999_2687355317616238590_n](https://user-images.githubusercontent.com/48614539/120886639-290afc80-c619-11eb-839e-1dd63e1ece40.png)


### IV. Chức năng Caculate The Hidden Area

- Tính toán phần trăm vùng mà camera nhìn thấy trong căn phòng 

![193779727_356318105843196_6894552735816920153_n](https://user-images.githubusercontent.com/48614539/120886680-4dff6f80-c619-11eb-8656-52314be799cc.png)

### V. Chức năng Display The Hidden Area

- Hiện thị hình chiếu vùng nhìn thấy lên các mặt của căn phòng trừ mặt trên 

![188804818_1035917173814425_7102112656160449139_n](https://user-images.githubusercontent.com/48614539/120886748-b77f7e00-c619-11eb-871b-4bdff47087fc.png)

### VI. Chức năng Display The Vision Of Camera 

- Hiện thị hình ảnh mà camera nhìn thấy trong căn phòng 

![189229766_580671316244362_5907220608318739434_n](https://user-images.githubusercontent.com/48614539/120886776-de3db480-c619-11eb-8f0e-3d3ee5be6b54.png)

### VII. Các chức năng còn lại 
- Chức năng *Exit* để thoát chương trình
- Các chức năng còn lại vẫn đang trong quá trình phát triển 
