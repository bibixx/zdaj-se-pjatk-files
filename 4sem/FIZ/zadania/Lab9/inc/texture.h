#ifndef __TEXTURE_H
#define __TEXTURE_H

#define BITMAP_ID 0x4D42	             
#define RGB_FORMAT    24                  
#define RGBA_FORMAT   32                 

#include <windows.h>
#include <stdio.h>
#include <GL\glut.h>


#ifdef _MSC_VER
#	pragma pack( push, packing )
#	pragma pack( 1 )
#	define PACK_STRUCT
#elif defined( __GNUC__ )
#	define PACK_STRUCT	__attribute__((packed))
#else
#	error compiler not supported
#endif

	struct SBMPHeader
	{
		unsigned short	Id;					//	BM - Windows 3.1x, 95, NT, 98, 2000, ME, XP
											//	BA - OS/2 Bitmap Array
											//	CI - OS/2 Color Icon
											//	CP - OS/2 Color Pointer
											//	IC - OS/2 Icon
											//	PT - OS/2 Pointer
		unsigned int	FileSize;
		unsigned int	Reserved;
		unsigned int	BitmapDataOffset;
		unsigned int	BitmapHeaderSize;	// should be 28h for windows bitmaps or
											// 0Ch for OS/2 1.x or F0h for OS/2 2.x
		unsigned int Width;
		unsigned int Height;
		unsigned short Planes;
		unsigned short BPP;					// 1: Monochrome bitmap
											// 4: 16 color bitmap
											// 8: 256 color bitmap
											// 16: 16bit (high color) bitmap
											// 24: 24bit (true color) bitmap
											// 32: 32bit (true color) bitmap

		unsigned int  Compression;			// 0: none (Also identified by BI_RGB)
											// 1: RLE 8-bit / pixel (Also identified by BI_RLE4)
											// 2: RLE 4-bit / pixel (Also identified by BI_RLE8)
											// 3: Bitfields  (Also identified by BI_BITFIELDS)

		unsigned int  BitmapDataSize;		// Size of the bitmap data in bytes. This number must be rounded to the next 4 byte boundary.
		unsigned int  PixelPerMeterX;
		unsigned int  PixelPerMeterY;
		unsigned int  Colors;
		unsigned int  ImportantColors;
	} PACK_STRUCT;


// Default alignment
#ifdef _MSC_VER
#	pragma pack( pop, packing )
#endif

#undef PACK_STRUCT




#define BITMAP_ID 0x4D42	// The universal bitmap ID


class CTexture
{
   public:
      CTexture();   // Constructor.
      ~CTexture();  // Destructor.

      bool LoadBMPFile(char *filename);   // Load a .bmp image file.
      void FreeImage();                   // Delete a image.

      unsigned int ID;                    // ID used for generating the textures in OpenGl.
      int imageWidth;                     // Width of a texture.
      int imageHeight;                    // Height of a texture.

   protected:
      void GenerateTexture();             // Generate a texture in OpenGL.
      unsigned char* LoadBitmap(char *file,
                                BITMAPINFOHEADER *bitmapInfoHeader);// Load a bitmap image.

      unsigned char *image;               // Texture image.
      bool textureExist;                  // This will be used if the image was loaded.
      int type;                           // Image format.
};


#endif
